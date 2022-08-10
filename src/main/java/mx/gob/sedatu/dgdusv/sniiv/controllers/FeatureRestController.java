package mx.gob.sedatu.dgdusv.sniiv.controllers;

import mx.gob.sedatu.dgdusv.sniiv.models.dto.PoligonoHash;
import mx.gob.sedatu.dgdusv.sniiv.models.dto.PoligonoJson;
import mx.gob.sedatu.dgdusv.sniiv.models.entity.FeatureInsus;
import mx.gob.sedatu.dgdusv.sniiv.models.entity.FeatureMexico;
import mx.gob.sedatu.dgdusv.sniiv.models.entity.PeriodoMapaInsus;
import mx.gob.sedatu.dgdusv.sniiv.models.services.*;

import java.util.*;

import org.locationtech.jts.geom.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gis-api")
public class FeatureRestController {

	@Autowired
	private IFeatureService featureService;

	@Autowired
	private IFeatureMexicoService featureMexicoService;

	@Autowired
	private IPeriodoMapaInsusService periodoMapaInsusService;

	//private GeometryFactory gf = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING),6372);

	@GetMapping("/getMaxByState")
	public ResponseEntity<Long> getMaxMinByState(@RequestParam boolean montos,@RequestParam Integer year,@RequestParam boolean filter){//true for max, false for min
		Long result = filter ?0l : 100000l;
		for(int i = 1; i <=32 ; i++){
			Long totalByState = montos ? featureService.getSumStateByTotal(String.format("%02d", i),year) : featureService.getSumAccionesStateByTotal(String.format("%02d", i),year);
			if(totalByState != null) {
				result = (filter ? (result < totalByState) : (result > totalByState)) ? totalByState : result;
			}
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/getMaxByTown")
	public ResponseEntity<Long> getMaxMinByTown(@RequestParam boolean montos,@RequestParam Integer year,@RequestParam boolean filter,@RequestParam String state){//true for max, false for min
		return ResponseEntity.ok(getAllByMuniOrPoli(state,"",year,filter,montos,true));
	}

	@GetMapping("/getMaxByPoli")
	public ResponseEntity<Long> getMaxMinByPoli(@RequestParam boolean montos,@RequestParam Integer year,@RequestParam boolean filter,@RequestParam String state,@RequestParam String muni){//true for max, false for min
		return ResponseEntity.ok(getAllByMuniOrPoli(state,muni,year,filter,montos,true));
	}


	@GetMapping("/poligonosmexico")
	public ResponseEntity<List<PoligonoJson>> poligonosMexico(@RequestParam boolean isMontos,@RequestParam Integer year,@RequestParam String filter, @RequestParam Long pgnumber, @RequestParam Long pgsize, @RequestParam Double xmin, @RequestParam Double xmax, @RequestParam Double ymin, @RequestParam Double ymax){
		Envelope en = new Envelope(xmin,xmax,ymin,ymax);
		//List<Feature> fa = featureService.findAll();
		Pageable pb = PageRequest.of(pgnumber.intValue(), pgsize.intValue());
		Page<FeatureMexico> pf = featureMexicoService.findAllPageable(pb);
		List<PoligonoJson> pj = new ArrayList<>();

		for(FeatureMexico p : pf) {
			if(filter.equals("MEX")){//get all estados
				if(Integer.parseInt(p.getClave_geo()) <= 32){
					if(en.intersects(p.getPol().getThe_geom().getCoordinate().getX(),p.getPol().getThe_geom().getCoordinate().getY())) {
						Long total = isMontos ? featureService.getSumStateByTotal(p.getClave_geo(),year) : featureService.getSumAccionesStateByTotal(p.getClave_geo(),year);
						if(total != null){
							pj.add(new PoligonoJson(p.getId(),p.getPol().getThe_geom().toString(),p.getClave_geo(),isMontos ? featureService.getSumStateByMen(p.getClave_geo(),year):featureService.getSumAccionesStateByMen(p.getClave_geo(),year),isMontos ? featureService.getSumStateByWomen(p.getClave_geo(),year) : featureService.getSumAccionesStateByWomen(p.getClave_geo(),year),total,getMaxMinByState(isMontos,year,true).getBody(),getMaxMinByState(isMontos,year,false).getBody()));
						}
					}
				}
			}else{
				if(p.getClave_geo().startsWith(filter) && !p.getClave_geo().equals(filter)){
					if(Integer.parseInt(p.getClave_geo()) > 32){
						if(en.intersects(p.getPol().getThe_geom().getCoordinate().getX(),p.getPol().getThe_geom().getCoordinate().getY())) {
							String state = p.getClave_geo().substring(0,2);
							String muni = p.getClave_geo().substring(2);
							Long total = isMontos ? featureService.getSumTownByTotal(state,muni,year) : featureService.getSumAccionesTownByTotal(state,muni,year);
							if(total != null) {
								pj.add(new PoligonoJson(p.getId(), p.getPol().getThe_geom().toString(), p.getClave_geo(), isMontos ? featureService.getSumTownByMen(state,muni,year) : featureService.getSumAccionesTownByMen(state,muni,year),isMontos ? featureService.getSumTownByWomen(state,muni,year) : featureService.getSumAccionesTownByWomen(state,muni,year), total, getMaxMinByTown(isMontos,year,true,state).getBody(), getMaxMinByTown(isMontos,year,false,state).getBody()));//p.getImporte_t(),p.getImporte_h(),p.getImporte_m()));
							}
						}
					}
				}
			}
		}
		return ResponseEntity.ok(pj);
	}

	@GetMapping("/poligonosinsus")
	public ResponseEntity<List<PoligonoJson>> poligonosInsus(@RequestParam boolean isMontos,@RequestParam Integer year,@RequestParam String filter, @RequestParam Long pgnumber, @RequestParam Long pgsize, @RequestParam Double xmin, @RequestParam Double xmax, @RequestParam Double ymin, @RequestParam Double ymax){
		Envelope en = new Envelope(xmin,xmax,ymin,ymax);
		//List<Feature> fa = featureService.findAll();
		Pageable pb = PageRequest.of(pgnumber.intValue(), pgsize.intValue());
		Page<FeatureInsus> pf = featureService.findAllPageable(pb);
		List<PoligonoJson> pj = new ArrayList<>();
		String estado = filter.substring(0,2);
		String muni = filter.substring(2);
		for(FeatureInsus p : pf) {
			if(en.intersects(p.getPol().getThe_geom().getCoordinate().getX(),p.getPol().getThe_geom().getCoordinate().getY())) {
				if (p.getClave_estado().equals(estado) && p.getClave_municipio().equals(muni)) {
					pj.add(new PoligonoJson(p.getId(), p.getPol().getThe_geom().toString(), p.getPoligono(), isMontos ? p.getImporte_t() : p.getAcciones(), isMontos ? p.getImporte_h() : p.getH(), isMontos ? p.getImporte_m() : p.getM(), getMaxMinByPoli(isMontos,year,true,estado,muni).getBody(), getMaxMinByPoli(isMontos,year,false,estado,muni).getBody()));
				}
			}
		}
		return ResponseEntity.ok(pj);
	}
	
	@GetMapping("/poliinsusall")
	public ResponseEntity<List<PoligonoJson>> allInsusPoints(){
		List<FeatureInsus> fa = featureService.findAll();
		List<PoligonoJson> pj = new ArrayList<>();
		for(FeatureInsus p : fa) {
			//System.out.println(p.getPol().getThe_geom().getCoordinate().getX());
			pj.add(new PoligonoJson(p.getId(),p.getPol().getThe_geom().toString(),p.getPoligono(),p.getImporte_t(),p.getImporte_h(),p.getImporte_m(),0l,0l));
		}
		//System.out.println("El tamaño de campos es: "+fa.size());
		return ResponseEntity.ok(pj);
	}

	@GetMapping("/polimexicoall")
	public ResponseEntity<List<PoligonoJson>> allMexicoPoints(){
		List<FeatureMexico> fa = featureMexicoService.findAll();
		List<PoligonoJson> pj = new ArrayList<>();
		for(FeatureMexico p : fa) {
			//System.out.println(p.getPol().getThe_geom().getCoordinate().getX());
			pj.add(new PoligonoJson(p.getId(),p.getPol().getThe_geom().toString(),p.getNombre(),new Long(0),new Long(0),new Long(0),0l,0l));//p.getPoligono(),p.getImporte_t(),p.getImporte_h(),p.getImporte_m()));
		}
		//System.out.println("El tamaño de campos es: "+fa.size());
		return ResponseEntity.ok(pj);
	}
	
	@GetMapping("/poligonosconteo")
	public ResponseEntity<Long> poligonosConteo(@RequestParam Integer year,@RequestParam String filter,@RequestParam Double xmin,@RequestParam Double xmax, @RequestParam Double ymin, @RequestParam Double ymax){
		String state = filter.substring(0,2);
		String muni = filter.substring(2);
		return ResponseEntity.ok(featureService.countAllByStateMuni(state,muni,year));
	}

	@GetMapping("/poligonosmexicoconteo")
	public ResponseEntity<Long> poligonosMexicoConteo(@RequestParam Integer year,@RequestParam String filter,@RequestParam Double xmin,@RequestParam Double xmax, @RequestParam Double ymin, @RequestParam Double ymax){

		if(filter.equals("MEX")){
			return ResponseEntity.ok(featureService.getCountOfStates(year));
		}else{
			return ResponseEntity.ok(featureService.getCountOfMuniFromState(filter,year));
		}
	}
	@GetMapping("/periodo")
	public ResponseEntity<Integer> lastPeriodo(){
		return ResponseEntity.ok(periodoMapaInsusService.getLastYear());
	}

	@GetMapping("/allPeriodos")
	public ResponseEntity<List<PeriodoMapaInsus>> allPeriodos(){ return ResponseEntity.ok(periodoMapaInsusService.findAll()); }
	
	@GetMapping("/predioidentify")
	public ResponseEntity<List<PoligonoHash>> encontrarPunto(@RequestParam boolean isMontos,@RequestParam Integer year,@RequestParam Long id,@RequestParam Integer level){
		List<PoligonoHash> poligonos = new ArrayList<>();
		switch (level){
			case 1://poligono
				FeatureInsus fa = featureService.findById(id);
				poligonos = fa.toListHash();
				break;
			case 2://Municipio
				FeatureMexico fmm = featureMexicoService.findById(id);
				String estado = fmm.getClave_geo().substring(0,2);
				String municipio = fmm.getClave_geo().substring(2);
				poligonos.add(new PoligonoHash("Estado", featureMexicoService.findNameByCveGeo(estado)));
				poligonos.add(new PoligonoHash("Municipio", fmm.getNombre()));
				if(isMontos) {
					poligonos.add(new PoligonoHash("Monto por mujeres", featureService.getSumTownByWomen(estado, municipio, year)));
					poligonos.add(new PoligonoHash("Monto por hombres", featureService.getSumTownByMen(estado, municipio, year)));
					poligonos.add(new PoligonoHash("Monto total", featureService.getSumTownByTotal(estado, municipio, year)));
				}else {
					poligonos.add(new PoligonoHash("Acciones por mujeres", featureService.getSumAccionesTownByWomen(estado, municipio, year)));
					poligonos.add(new PoligonoHash("Acciones por hombres", featureService.getSumAccionesTownByMen(estado, municipio, year)));
					poligonos.add(new PoligonoHash("Acciones", featureService.getSumAccionesTownByTotal(estado, municipio, year)));
				}
				break;
			case 3://Estado
				FeatureMexico fme = featureMexicoService.findById(id);
				poligonos.add(new PoligonoHash("Estado", fme.getNombre()));
				if(isMontos) {
					poligonos.add(new PoligonoHash("Monto por mujeres", featureService.getSumStateByWomen(fme.getClave_geo(), year)));
					poligonos.add(new PoligonoHash("Monto por hombres", featureService.getSumStateByMen(fme.getClave_geo(), year)));
					poligonos.add(new PoligonoHash("Monto total", featureService.getSumStateByTotal(fme.getClave_geo(), year)));
				}else {
					poligonos.add(new PoligonoHash("Acciones por mujeres", featureService.getSumAccionesStateByWomen(fme.getClave_geo(), year)));
					poligonos.add(new PoligonoHash("Acciones por hombres", featureService.getSumAccionesStateByMen(fme.getClave_geo(), year)));
					poligonos.add(new PoligonoHash("Acciones", featureService.getSumAccionesStateByTotal(fme.getClave_geo(), year)));
				}
				break;
			default:
				break;
		}
		return poligonos.size()>0 ? ResponseEntity.ok(poligonos):ResponseEntity.status(409).build();
	}

	private Long getAllByMuniOrPoli(String state,String muni,Integer year,boolean filter,boolean isMontos,boolean isTown){
		Long result = filter ?0l : 100000l;
		List<FeatureInsus> featuresByTown = isTown ? featureService.getAllByState(state,year): featureService.getAllByMuni(state,muni,year);
		for(int i = 0; i < featuresByTown.size(); i++){
			Long totalByState = isMontos ? featuresByTown.get(i).getImporte_t() : featuresByTown.get(i).getAcciones();
			if(totalByState != null) {
				result = (filter ? (result < totalByState) : (result > totalByState)) ? totalByState : result;
			}
		}
		return result;
	}
}