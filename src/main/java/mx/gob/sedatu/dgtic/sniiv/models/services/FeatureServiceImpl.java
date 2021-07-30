package mx.gob.sedatu.dgtic.sniiv.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.sedatu.dgtic.sniiv.models.dao.IFeatureDao;
import mx.gob.sedatu.dgtic.sniiv.models.entity.Feature;

@Service
public class FeatureServiceImpl implements IFeatureService{

	@Autowired
	private IFeatureDao featureDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Feature> findAll() {
		return (List<Feature>)featureDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Feature> findAllPageable(Pageable pg) {
		return (Page<Feature>)featureDao.findAll(pg);
	}
	
	@Override
	public Feature save(Feature feature) {
		return featureDao.save(feature);
	}

	@Override
	public void delete(Long id) {
		featureDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Feature findById(Long id) {
		return featureDao.findById(id).get();
	}

}
