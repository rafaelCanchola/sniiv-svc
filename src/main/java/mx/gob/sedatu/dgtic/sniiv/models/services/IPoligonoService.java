package mx.gob.sedatu.dgtic.sniiv.models.services;

import java.util.List;

import mx.gob.sedatu.dgtic.sniiv.models.entity.Poligono;


public interface IPoligonoService {
	
	public List<Poligono> findAll();

	public Poligono save(Poligono poligono);
	
	public void delete(Long id);
	
	public Poligono findById(Long id);

}
