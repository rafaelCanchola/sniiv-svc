package mx.gob.sedatu.dgtic.sniiv.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.gob.sedatu.dgtic.sniiv.models.entity.Feature;

public interface IFeatureService {
	
	public List<Feature> findAll();

	public Feature save(Feature poligono);
	
	public void delete(Long id);
	
	public Feature findById(Long id);

	public Page<Feature> findAllPageable(Pageable pg);



}
