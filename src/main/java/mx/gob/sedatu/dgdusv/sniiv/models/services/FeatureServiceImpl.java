package mx.gob.sedatu.dgdusv.sniiv.models.services;

import java.util.List;

import mx.gob.sedatu.dgdusv.sniiv.models.dao.IFeatureDao;
import mx.gob.sedatu.dgdusv.sniiv.models.entity.FeatureInsus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeatureServiceImpl implements IFeatureService{

	@Autowired
	private IFeatureDao featureDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<FeatureInsus> findAll() {
		return (List<FeatureInsus>)featureDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<FeatureInsus> findAllPageable(Pageable pg) {
		return (Page<FeatureInsus>)featureDao.findAll(pg);
	}
	
	@Override
	public FeatureInsus save(FeatureInsus featureInsus) {
		return featureDao.save(featureInsus);
	}

	@Override
	public void delete(Long id) {
		featureDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public FeatureInsus findById(Long id) {
		return featureDao.findById(id).get();
	}

	@Override
	@Transactional(readOnly = true)
	public Long countAll() { return featureDao.count(); }

	@Override
	@Transactional(readOnly = true)
	public Long countAllByStateMuni(String state, String muni,Integer year) { return featureDao.getCountByStateMuni(state,muni,year); }

	@Override
	@Transactional(readOnly = true)
	public Long getSumAccionesStateByTotal(String state,Integer year){ return featureDao.getSumAccionesStateByTotal(state,year); }

	@Override
	@Transactional(readOnly = true)
	public Long getSumAccionesStateByMen(String state,Integer year){ return featureDao.getSumAccionesStateByMen(state,year); }

	@Override
	@Transactional(readOnly = true)
	public Long getSumAccionesStateByWomen(String state,Integer year){ return featureDao.getSumAccionesStateByWomen(state,year); }

	@Override
	@Transactional(readOnly = true)
	public Long getSumStateByTotal(String state,Integer year){
		return featureDao.getSumStateByTotal(state,year);
	}

	@Override
	@Transactional(readOnly = true)
	public Long getSumStateByMen(String state,Integer year){
		return featureDao.getSumStateByMen(state,year);
	}

	@Override
	@Transactional(readOnly = true)
	public Long getSumStateByWomen(String state,Integer year){
		return featureDao.getSumStateByWomen(state,year);
	}

	@Override
	@Transactional(readOnly = true)
	public Long getSumTownByTotal(String state,String muni,Integer year){ return featureDao.getSumTownByTotal(state,muni,year); }

	@Override
	@Transactional(readOnly = true)
	public Long getSumTownByMen(String state,String muni,Integer year){ return featureDao.getSumTownByMen(state,muni,year); }

	@Override
	@Transactional(readOnly = true)
	public Long getSumTownByWomen(String state,String muni,Integer year){ return featureDao.getSumTownByWomen(state,muni,year); }

	@Override
	@Transactional(readOnly = true)
	public List<FeatureInsus> getAllByState(String state,Integer year){
		return featureDao.getAllByState(state,year);
	}

	@Override
	@Transactional(readOnly = true)
	public List<FeatureInsus> getAllByMuni(String state, String muni,Integer year) { return featureDao.getAllByMuni(state,muni,year); }

	@Override
	@Transactional(readOnly = true)
	public Long getCountOfStates(Integer year){return featureDao.getCountOfStates(year);}

	@Override
	@Transactional(readOnly = true)
	public Long getCountOfMuniFromState(String state,Integer year){return featureDao.getCountOfMuniFromState(state,year);}

	@Override
	@Transactional(readOnly = true)
	public Long getSumAccionesTownByTotal(String state, String muni,Integer year) { return featureDao.getSumAccionesTownByTotal(state, muni,year); }

	@Override
	@Transactional(readOnly = true)
	public Long getSumAccionesTownByMen(String state, String muni,Integer year) { return featureDao.getSumAccionesTownByMen(state, muni,year); }

	@Override
	@Transactional(readOnly = true)
	public Long getSumAccionesTownByWomen(String state, String muni,Integer year) { return featureDao.getSumAccionesTownByWomen(state, muni,year); }

	@Override
	@Transactional(readOnly = true)
	public FeatureInsus featureExists(String nomPoli,Integer year) {
		return featureDao.featureExists(nomPoli,year);
	}
}
