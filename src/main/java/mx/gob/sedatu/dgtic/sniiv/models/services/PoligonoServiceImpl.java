package mx.gob.sedatu.dgtic.sniiv.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.sedatu.dgtic.sniiv.models.dao.IPoligonoDao;
import mx.gob.sedatu.dgtic.sniiv.models.entity.Poligono;

@Service
public class PoligonoServiceImpl implements IPoligonoService{

	@Autowired
	private IPoligonoDao poligonoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Poligono> findAll() {
		return (List<Poligono>)poligonoDao.findAll();
	}

	@Override
	public Poligono save(Poligono poligono) {
		return poligonoDao.save(poligono);
	}

	@Override
	public void delete(Long id) {
		poligonoDao.deleteById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Poligono findById(Long id) {
		return poligonoDao.findById(id).get();
	}

}
