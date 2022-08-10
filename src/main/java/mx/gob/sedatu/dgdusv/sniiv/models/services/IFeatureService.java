package mx.gob.sedatu.dgdusv.sniiv.models.services;

import java.util.List;

import mx.gob.sedatu.dgdusv.sniiv.models.entity.FeatureInsus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface IFeatureService {
	
	List<FeatureInsus> findAll();

	FeatureInsus save(FeatureInsus poligono);
	
	void delete(Long id);
	
	FeatureInsus findById(Long id);

	Page<FeatureInsus> findAllPageable(Pageable pg);

	Long countAll();

	Long countAllByStateMuni(String state,String muni,Integer year);

	Long getSumAccionesStateByTotal(String state,Integer year);

	Long getSumAccionesStateByMen(String state,Integer year);

	Long getSumAccionesStateByWomen(String state,Integer year);

	Long getSumStateByTotal(String state,Integer year);

	Long getSumStateByMen(String state,Integer year);

	Long getSumStateByWomen(String state,Integer year);

	Long getSumTownByTotal(String state,String muni,Integer year);

	Long getSumTownByMen(String state,String muni,Integer year);

	Long getSumTownByWomen(String state,String muni,Integer year);

	List<FeatureInsus> getAllByState(String state,Integer year);

	List<FeatureInsus> getAllByMuni(String state,String muni,Integer year);

	Long getCountOfStates(Integer year);

	Long getCountOfMuniFromState(String state,Integer year);

	Long getSumAccionesTownByTotal(String state,String muni,Integer year);

	Long getSumAccionesTownByMen(String state,String muni,Integer year);

	Long getSumAccionesTownByWomen(String state,String muni,Integer year);

	FeatureInsus featureExists(String nomPoli);


}
