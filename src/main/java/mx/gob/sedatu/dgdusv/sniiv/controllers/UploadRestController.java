package mx.gob.sedatu.dgdusv.sniiv.controllers;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import mx.gob.sedatu.dgdusv.sniiv.models.dto.PoligonoJson;
import mx.gob.sedatu.dgdusv.sniiv.models.entity.*;
import mx.gob.sedatu.dgdusv.sniiv.models.services.*;
import mx.gob.sedatu.dgdusv.sniiv.utilities.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

@RestController
@RequestMapping("/api")
public class UploadRestController {

    @Autowired
    private IPoligonoService poligonoService;

    @Autowired
    private ICuboInsusService cuboInsusService;

    @Autowired
    private IFeatureService featureService;

    @Autowired
    private IFeatureMexicoService featureMexicoService;

    @Autowired
    private IPeriodoMapaInsusService periodoMapaInsusService;

    @Autowired
    private IPnvObjetivosService pnvObjetivosService;

    @Autowired
    private IPnvAccionesService pnvAccionesService;

    @Autowired
    private IPnvInformesService pnvInformesService;
    @Autowired
    private Environment env;

    @Autowired
    private ShapeFileUtils shapeUtils;

    @Autowired
    private Utils myUtils;

    @Autowired
    private HttpMethods httpMethods;

    @Autowired
    private ApiAlfresco apiAlfresco;

    @GetMapping("/prueba")
    public ResponseEntity<String> prueba(){
        return ResponseEntity.ok(Constants.AlfrescoEndPoints.NODES);
    }

    @PostMapping("/uploadpnvreporte")
    public ResponseEntity<String> cargarReportePnvAlfresco(@RequestParam MultipartFile file) {
        Integer year;
        if(!file.getOriginalFilename().contains(Constants.InformePnv.PNV_NAME) || !file.getOriginalFilename().endsWith(Constants.PDF_EXT)){
            return ResponseEntity.status(401).build();
        }
        Optional<Integer> trimestre = TrimestreEnum.getTrimestreFromLabel(file.getOriginalFilename().split(Constants.InformePnv.SPLIT_CHAR)[0]);
        if(!trimestre.isPresent()){
            return ResponseEntity.status(401).build();
        }
        try{
            year = Integer.parseInt("20"+(file.getOriginalFilename().split(Constants.InformePnv.SPLIT_CHAR)[4]).substring(0,2));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(401).build();
        }
        try {
            JSONObject postUpload = new JSONObject();
            JSONObject jsonObj;
            postUpload.put("name",file);
            postUpload.put("nodeType","cm:content");
            String response = httpMethods.postFormHttpMethod(apiAlfresco.folderNode(Constants.AlfrescoFolders.FOLDER_PNV_INFORMES),file.getOriginalFilename(),file);
            jsonObj = new JSONObject(response);
            String id = ((JSONObject)jsonObj.get("entry")).get("id").toString();
            postUpload = new JSONObject();
            postUpload.put("nodeId",id);
            response = httpMethods.postHttp(apiAlfresco.generateUrl(),postUpload.toString(), httpMethods.authHeader());
            jsonObj = new JSONObject(response);
            id = ((JSONObject)jsonObj.get("entry")).get("id").toString();
            PnvInformes informe = new PnvInformes();
            informe.setAnio(year);
            informe.setTrimestre(trimestre.get());
            informe.setUrl(apiAlfresco.sharedUrlAlfresco(id,file.getOriginalFilename()));
            pnvInformesService.save(informe);
            return ResponseEntity.ok(informe.getUrl());
        }catch (IOException e){
            e.printStackTrace();
            //error in httpmethod
            return ResponseEntity.status(402).build();
        } catch (JSONException e) {
            e.printStackTrace();
            //Error in json generation
            return ResponseEntity.status(411).build();
        }
    }

    @PostMapping("/uploadpnv")
    public ResponseEntity<List<PoligonoJson>> cargarPnvAcciones(@RequestParam MultipartFile file) {
        int csvLines = 9;
        Path tempDir = null;
        File csvFile = null;
        try {
            tempDir = Files.createTempDirectory(Constants.TEMP_DIRECTORY);
        }catch (Exception e) {
            e.printStackTrace();
            //not enough space on disk or write/read error
            return ResponseEntity.status(400).build();
        }
        if(!file.getOriginalFilename().startsWith(Constants.CSV_NAME)){
            tempDir.toFile().delete();
            //file is not the right type
            return ResponseEntity.status(401).build();
        }
        try {
            csvFile = new File(tempDir.toFile(), file.getOriginalFilename());
            file.transferTo(csvFile);
            CSVReader reader = new CSVReader(new FileReader(tempDir+"/"+file.getOriginalFilename()));
            String[] lineInArray;
            String compartidas = "COMPARTIDAS";
            String[] placeColumnNames = {"anio","trimestre","organismo","tipo_objetivo","concluida","en_proceso","por_iniciar","sin_realizar","total"};
            String[] columnNames = reader.readNext();
            for(int i = 0;i < csvLines; i++){
                if(!placeColumnNames[i].equals(columnNames[i])){
                    return ResponseEntity.status(401).build();
                }
            }
            while ((lineInArray = reader.readNext()) != null) {
                if(lineInArray[2].equals(compartidas)){
                    PnvAcciones acciones = new PnvAcciones();
                    acciones.setAnio(Integer.parseInt(lineInArray[0]));
                    acciones.setTrimestre(Integer.parseInt(lineInArray[1]));
                    acciones.setObjetivo(Integer.parseInt(lineInArray[3]));
                    for(int j = 4;j < 8;j++){
                        if(lineInArray[j].equals("1") ){
                            acciones.setEstatus(j-3);
                        }
                    }
                    PnvAcciones find = pnvAccionesService.findByInformation(acciones.getAnio(),acciones.getTrimestre(),acciones.getObjetivo(),acciones.getEstatus());
                    if (find != null) {
                        acciones.setId(find.getId());
                    }
                    acciones.setTotal_compartidas(Integer.parseInt(lineInArray[8]));
                    pnvAccionesService.save(acciones);
                }else{
                    PnvObjetivos objetivos = new PnvObjetivos();
                    objetivos.setAnio(Integer.parseInt(lineInArray[0]));
                    objetivos.setTrimestre(Integer.parseInt(lineInArray[1]));
                    objetivos.setOrganismo(lineInArray[2]);
                    objetivos.setTipo_objetivo(Integer.parseInt(lineInArray[3]));
                    objetivos.setConcluida(Integer.parseInt(lineInArray[4]));
                    objetivos.setEn_proceso(Integer.parseInt(lineInArray[5]));
                    objetivos.setPor_iniciar(Integer.parseInt(lineInArray[6]));
                    objetivos.setSin_realizar(Integer.parseInt(lineInArray[7]));
                    objetivos.setTotal(Integer.parseInt(lineInArray[8]));
                    pnvObjetivosService.save(objetivos);
                }
            }
        }  catch (IOException e) {
            e.printStackTrace();
            if(csvFile != null) {
                csvFile.delete();
            }
            if(tempDir != null) {
                tempDir.toFile().delete();
            }
            return ResponseEntity.status(400).build();
        } catch (CsvValidationException e) {
            e.printStackTrace();
            if(csvFile != null) {
                csvFile.delete();
            }
            if(tempDir != null) {
                tempDir.toFile().delete();
            }
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(null);
    }


        @PostMapping("/uploadchargepoli")
    public ResponseEntity<List<PoligonoJson>> cargarPoligonosTempFolder(@RequestParam MultipartFile file){
        String fileName = myUtils.getFileNameUpload(file.getOriginalFilename().split(Constants.ZIP_EXT)[0]);
        String zipFileName = fileName + Constants.ZIP_EXT;
        Path tempDir = null;
        File zipFile = null;
        try {
            tempDir = Files.createTempDirectory(Constants.TEMP_DIRECTORY);
        }catch (Exception e) {
            e.printStackTrace();
            //not enough space on disk or write/read error
            return ResponseEntity.status(400).build();
        }
        if(!zipFileName.startsWith(Constants.SHAPE_NAME)){
            tempDir.toFile().delete();
            //file is not the right type
            return ResponseEntity.status(401).build();
        }
        try{
            zipFile = new File(tempDir.toFile(), zipFileName);
            file.transferTo(zipFile);
            TreeMap tm = new TreeMap();
            UncompressorZIP unzip = new UncompressorZIP();
            unzip.descomprimeZip(tempDir+"/"+zipFileName, tm, tempDir+"/"+fileName);
            List<String> shapesNames = new ArrayList<>();
            File fileTempDir = new File(tempDir+"/"+fileName);
            for (File fileTemp : fileTempDir.listFiles()) {
                if (fileTemp.getName().toUpperCase().contains(Constants.SHAPE_EXT)) {
                    shapesNames.add(fileTemp.getPath());
                }
            }
            if(shapesNames.isEmpty()) {
                deleteZip(tempDir+"/"+zipFileName,tempDir+"/"+fileName);
                return ResponseEntity.status(405).build();
            }else {
                //SET SHAPE
                boolean validation = isInformationValidUpload(shapesNames,zipFileName);
                deleteZip(tempDir+"/"+zipFileName,tempDir+"/"+fileName);
                tempDir.toFile().delete();
                return ResponseEntity.status(validation ?200:408).build();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            if(zipFile != null) {
                zipFile.delete();
            }
            if(tempDir != null) {
                tempDir.toFile().delete();
            }
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/uploadchargeold")
    public ResponseEntity<List<PoligonoJson>> cargarPoligonos(@RequestParam MultipartFile file){
        TreeMap tm = new TreeMap();
        UncompressorZIP unzip = new UncompressorZIP();
        String nameTempDir = env.getProperty(Constants.DIRECTORY_UPLOAD_ROOT)+env.getProperty(Constants.DIRECTORY_UPLOAD_SNIIV)+env.getProperty(Constants.DIRECTORY_UPLOAD_SNIIV_TEMP);
        String namePuntosDir = env.getProperty(Constants.DIRECTORY_UPLOAD_ROOT)+env.getProperty(Constants.DIRECTORY_UPLOAD_SNIIV)+env.getProperty(Constants.DIRECTORY_UPLOAD_SNIIV_PUNTOS);
        String fileName = myUtils.getFileNameUpload(file.getOriginalFilename().split(Constants.ZIP_EXT)[0]);
        String zipFileName = fileName + Constants.ZIP_EXT;
        File baseDir = new File(env.getProperty(Constants.DIRECTORY_UPLOAD_ROOT)+"/"+Constants.SEDATU);
        File baseSniivDir = new File(env.getProperty(Constants.DIRECTORY_UPLOAD_ROOT)+env.getProperty(Constants.DIRECTORY_UPLOAD_SNIIV));
        File chargeTempDir = new File(nameTempDir);
        File chargePuntosDir = new File(namePuntosDir);
        File fileTempDir = new File(nameTempDir+fileName);

        if(!(myUtils.checkFolder(baseDir) && myUtils.checkFolder(baseSniivDir) && myUtils.checkFolder(chargeTempDir) && myUtils.checkFolder(chargePuntosDir) && myUtils.checkFolder(fileTempDir) && zipFileName.startsWith(Constants.SHAPE_NAME))){
            deleteZip(namePuntosDir+zipFileName,nameTempDir+fileName);
            return ResponseEntity.status(400).build();
        }
        try {
            file.transferTo(new File(namePuntosDir,zipFileName));
            unzip.descomprimeZip(namePuntosDir+zipFileName, tm, nameTempDir+fileName);
            List<String> shapesNames = new ArrayList<>();
            for (File fileTemp : fileTempDir.listFiles()) {
                if (fileTemp.getName().toUpperCase().contains(Constants.SHAPE_EXT)) {
                    shapesNames.add(fileTemp.getPath());
                }
            }
            if(shapesNames.isEmpty()) {
                deleteZip(namePuntosDir+zipFileName,nameTempDir+fileName);
                return ResponseEntity.status(405).build();
            }else {
                //SET SHAPE
                boolean validation = isInformationValidUpload(shapesNames,zipFileName);
                deleteZip(namePuntosDir+zipFileName,nameTempDir+fileName);
                return ResponseEntity.status(validation ?200:408).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            deleteZip(namePuntosDir+zipFileName,nameTempDir+fileName);
            return ResponseEntity.status(409).build();
        }
    }

    @PostMapping("/uploadcharge")
    public ResponseEntity<String> cargarPoligonosAlfresco(@RequestParam MultipartFile file) {
        JSONObject postUpload = new JSONObject();
        JSONObject jsonObj;
        String fileName = myUtils.getFileNameUpload(file.getOriginalFilename().split(Constants.ZIP_EXT)[0]);
        String zipFileName = fileName + Constants.ZIP_EXT;
        try {
            postUpload.put("name",zipFileName);
            postUpload.put("nodeType","cm:content");
            String response = httpMethods.postFormHttpMethod(apiAlfresco.folderNode(Constants.AlfrescoFolders.FOLDER_ZIPS),zipFileName,file);
            jsonObj = new JSONObject(response);
            String id = ((JSONObject)jsonObj.get("entry")).get("id").toString();

            return ResponseEntity.ok(response);
        }catch (IOException e){
            e.printStackTrace();
            //error in httpmethod
            return ResponseEntity.status(400).build();
        } catch (JSONException e) {
            e.printStackTrace();
            //Error in json generation
            return ResponseEntity.status(411).build();
        }
    }


    private void deleteZip(String zipLoc, String folderLoc){
        (new File(zipLoc)).delete();
        myUtils.deleteDirectory(new File(folderLoc));
    }

    private boolean isInformationValidUpload(List<String> shapesNames,String type) {
        try {
            if (type.contains(Constants.POLIGONO_NAME)) {
                List<Poligono> poligonoInsert = shapeUtils.readGeometryIntoPolygon(shapesNames);
                List<FeatureInsus> shapeInsert = shapeUtils.readShapeIntoObjectPredio(shapesNames);
                for (PeriodoMapaInsus period : periodoMapaInsusService.findAll()){
                    if(shapeInsert.get(0).getAnio().equals(period.getAnio())){
                        return false;
                    }
                }
                PeriodoMapaInsus per = new PeriodoMapaInsus();
                per.setAnio(shapeInsert.get(0).getAnio());
                periodoMapaInsusService.save(per);
                for (int i = 0; i < shapeInsert.size(); i++) {
                    shapeInsert.get(i).setImporte_t(cuboInsusService.getMontosByPolygon(shapeInsert.get(i).getPoligono(),shapeInsert.get(i).getAnio()));
                    shapeInsert.get(i).setImporte_h(cuboInsusService.getMontosByMenPolygon(shapeInsert.get(i).getPoligono(),shapeInsert.get(i).getAnio()));
                    shapeInsert.get(i).setImporte_m(cuboInsusService.getMontosByWomenPolygon(shapeInsert.get(i).getPoligono(),shapeInsert.get(i).getAnio()));
                    shapeInsert.get(i).setH(cuboInsusService.getAccionesByMenPolygon(shapeInsert.get(i).getPoligono(),shapeInsert.get(i).getAnio()));
                    shapeInsert.get(i).setM(cuboInsusService.getAccionesByWomenPolygon(shapeInsert.get(i).getPoligono(),shapeInsert.get(i).getAnio()));
                    Poligono jpaPoligono = poligonoService.save(poligonoInsert.get(i));
                    shapeInsert.get(i).setPol(jpaPoligono);
                    FeatureInsus jpaShape = featureService.save(shapeInsert.get(i));
                }
                return true;
            } else if (type.contains(Constants.ESTADO_NAME)||type.contains(Constants.MUNICIPIO_NAME)) {
                List<FeatureMexico> shapeInsert = shapeUtils.readShapeIntoObjectEdo(shapesNames);
                List<Poligono> poligonoInsert = shapeUtils.readGeometryIntoPolygon(shapesNames);
                for (int i = 0; i < shapeInsert.size(); i++) {
                    Poligono jpaPoligono = poligonoService.save(poligonoInsert.get(i));
                    shapeInsert.get(i).setPol(jpaPoligono);
                    FeatureMexico jpaShape = featureMexicoService.save(shapeInsert.get(i));
                }
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
