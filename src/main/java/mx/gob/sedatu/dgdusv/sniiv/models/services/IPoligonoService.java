package mx.gob.sedatu.dgdusv.sniiv.models.services;

import java.util.List;

import mx.gob.sedatu.dgdusv.sniiv.models.entity.Poligono;

public interface IPoligonoService {
	
	List<Poligono> findAll();

	Poligono save(Poligono poligono);
	
	void delete(Long id);
	
	Poligono findById(Long id);

}
