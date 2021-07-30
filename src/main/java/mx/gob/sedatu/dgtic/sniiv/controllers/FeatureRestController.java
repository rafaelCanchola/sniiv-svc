package mx.gob.sedatu.dgtic.sniiv.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.locationtech.jts.geom.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mx.gob.sedatu.dgtic.sniiv.models.dto.PoligonoHash;
import mx.gob.sedatu.dgtic.sniiv.models.dto.PoligonoJson;
import mx.gob.sedatu.dgtic.sniiv.models.entity.Feature;
import mx.gob.sedatu.dgtic.sniiv.models.entity.Poligono;
import mx.gob.sedatu.dgtic.sniiv.models.services.IFeatureService;
import mx.gob.sedatu.dgtic.sniiv.models.services.IPoligonoService;
import mx.gob.sedatu.dgtic.sniiv.utilities.ShapeFileUtils;
import mx.gob.sedatu.dgtic.sniiv.utilities.UncompressorZIP;
import mx.gob.sedatu.dgtic.sniiv.utilities.Utils;

@RestController
@RequestMapping("/api")
public class FeatureRestController {
	
	@Autowired
	private IFeatureService featureService;
	
	@Autowired
	private IPoligonoService poligonoService;
	
	@Autowired
	private Environment env;
	
	
	//private GeometryFactory gf = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING),6372);
	
	@GetMapping("/poligonos")
	public ResponseEntity<List<PoligonoJson>> poligonos(@RequestParam Long filter,@RequestParam Long pgnumber,@RequestParam Long pgsize,@RequestParam Double xmin,@RequestParam Double xmax, @RequestParam Double ymin, @RequestParam Double ymax){
		Envelope en = new Envelope(xmin,xmax,ymin,ymax);
		//List<Feature> fa = featureService.findAll();
		Pageable pb = PageRequest.of(pgnumber.intValue(), pgsize.intValue());
		Page<Feature> pf = featureService.findAllPageable(pb);
		List<PoligonoJson> pj = new ArrayList<PoligonoJson>();
		for(Feature p : pf) {
			if(en.intersects(p.getPoligono().getThe_geom().getCoordinate().getX(),p.getPoligono().getThe_geom().getCoordinate().getY())) {
					pj.add(new PoligonoJson(p.getId(),p.getPoligono().getThe_geom().toString(),p.getCvegeo(),p.getDens_ha()));
					}
			}
		return ResponseEntity.ok(pj);
	}
	
	@GetMapping("/poliall")
	public ResponseEntity<List<PoligonoJson>> allPoints(){
		List<Feature> fa = featureService.findAll();
		List<PoligonoJson> pj = new ArrayList<PoligonoJson>();
		for(Feature p : fa) {
			System.out.println(p.getPoligono().getThe_geom().getCoordinate().getX());
			pj.add(new PoligonoJson(p.getId(),p.getPoligono().getThe_geom().toString(),p.getCvegeo(),p.getDens_ha()));
		}
		System.out.println("El tamaño de campos es: "+fa.size()); 
		return ResponseEntity.ok(pj);
	}
	
	@GetMapping("/poligonosconteo")
	public ResponseEntity<Integer> poligonosConteo(@RequestParam Long filter,@RequestParam Double xmin,@RequestParam Double xmax, @RequestParam Double ymin, @RequestParam Double ymax){
		Envelope en = new Envelope(xmin,xmax,ymin,ymax);
		List<Feature> fa = featureService.findAll();
		List<PoligonoJson> pj = new ArrayList<PoligonoJson>();
		for(Feature p : fa) {
			if(en.intersects(p.getPoligono().getThe_geom().getCoordinate().getX(),p.getPoligono().getThe_geom().getCoordinate().getY())) {
				pj.add(new PoligonoJson(p.getId(),p.getPoligono().getThe_geom().toString(),p.getCvegeo(),p.getDens_ha()));
			}
		}
		System.out.println("El tamaño de campos es: "+pj.size()); 
		return ResponseEntity.ok(pj.size());
	}
	
	@GetMapping("/predioidentify")
	public ResponseEntity<List<PoligonoHash>> encontrarPunto(@RequestParam Long id){
		Feature fa = featureService.findById(id);
		return ResponseEntity.ok(fa.toListHash());
	}
	
	
	@PostMapping("/uploadcharge")
	public ResponseEntity<List<PoligonoJson>> cargarPoligonos(@RequestParam Integer currentyear,@RequestParam MultipartFile file) throws IllegalStateException, IOException {
		TreeMap tm = new TreeMap();
		UncompressorZIP unzip = new UncompressorZIP();
		String nameTempDir = env.getProperty("app.upload.dir")+env.getProperty("app.upload.sniiv")+currentyear+env.getProperty("app.upload.sniiv.temporal");
		String namePuntosDir = env.getProperty("app.upload.dir")+env.getProperty("app.upload.sniiv")+currentyear+env.getProperty("app.upload.sniiv.puntos");
		String fileName = Utils.getFileNameUpload(file.getOriginalFilename().split(".zip")[0],currentyear);
		String zipFileName = fileName + ".zip";
		File baseDir = new File(env.getProperty("app.upload.dir")+"/sedatu");
		File baseSniivDir = new File(env.getProperty("app.upload.dir")+env.getProperty("app.upload.sniiv"));
		File baseSniivYearDir = new File(env.getProperty("app.upload.dir")+env.getProperty("app.upload.sniiv")+"2021/");
		File chargeTempDir = new File(nameTempDir);
		File chargePuntosDir = new File(namePuntosDir);
		File fileTempDir = new File(nameTempDir+fileName);
		if(!baseDir.exists()) {
			if(!baseDir.mkdir()) {
				return ResponseEntity.status(400).build();
			}
		}
		if(!baseSniivDir.exists()) {
			if(!baseSniivDir.mkdir()) {
				return ResponseEntity.status(400).build();
			}
		}
		if(!baseSniivYearDir.exists()) {
			if(!baseSniivYearDir.mkdir()) {
				return ResponseEntity.status(400).build();
			}
		}
		if(!chargeTempDir.exists()) {
			if(!chargeTempDir.mkdir()) {
				return ResponseEntity.status(400).build();
			}
		}
		if(!chargePuntosDir.exists()) {
			if(!chargePuntosDir.mkdir()) {
				return ResponseEntity.status(400).build();
			}
		}
		if(!fileTempDir.exists()) {
			if(!fileTempDir.mkdir()) {
				return ResponseEntity.status(400).build();
			}
		}
		file.transferTo(
				new File(namePuntosDir,zipFileName) 
		);
		try {
			unzip.descomprimeZip(namePuntosDir+zipFileName, tm, nameTempDir+fileName);
			List<String> shapesNames = new ArrayList<>();
	        for (File fileTemp : fileTempDir.listFiles()) {
	            if (fileTemp.getName().toUpperCase().contains(".SHP")) {
	                shapesNames.add(fileTemp.getPath());
	            }
	        }
	        if(shapesNames.isEmpty()) {
				return ResponseEntity.status(409).build();
	        }else {
	        	List<Feature> shapeInsert = ShapeFileUtils.readShapeIntoObject(shapesNames);
	        	List<Poligono> poligonoInsert = ShapeFileUtils.readGeometryIntoPolygon(shapesNames);
	        	if(shapeInsert.isEmpty()) {
					return ResponseEntity.status(409).build();
	        	}else {
	        		for(int i = 0; i < shapeInsert.size();i++) {
	        			Poligono jpaPoligono = poligonoService.save(poligonoInsert.get(i));
	        			shapeInsert.get(i).setPoligono(jpaPoligono);
	        			//SET SHAPE
	        			Feature jpaShape = featureService.save(shapeInsert.get(i));
	        		
	        		}
	        		System.out.println("Proceso finalizado");
	        		return allPoints();	
	        	}
	        }
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(409).build();
			
		}
	
	}
	
}
