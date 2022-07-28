package mx.gob.sedatu.dgdusv.sniiv.models.services;

import mx.gob.sedatu.dgdusv.sniiv.models.dao.IFeatureMexicoDao;
import mx.gob.sedatu.dgdusv.sniiv.models.entity.FeatureMexico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeatureMexicoServiceImpl implements IFeatureMexicoService {

	@Autowired
	private IFeatureMexicoDao featureMexicoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<FeatureMexico> findAll() {
		return (List<FeatureMexico>) featureMexicoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<FeatureMexico> findAllPageable(Pageable pg) {
		return (Page<FeatureMexico>) featureMexicoDao.findAll(pg);
	}
	
	@Override
	public FeatureMexico save(FeatureMexico featureMexico) {
		return featureMexicoDao.save(featureMexico);
	}

	@Override
	public void delete(Long id) {
		featureMexicoDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public FeatureMexico findById(Long id) {
		return featureMexicoDao.findById(id).get();
	}

	@Override
	@Transactional(readOnly = true)
	public Long countAll() {
		return featureMexicoDao.count();
	}

	@Override
	@Transactional(readOnly = true)
	public Long countState() {
		return featureMexicoDao.getStateConteoByName();
	}

	@Override
	@Transactional(readOnly = true)
	public Long countMuniFromState(String state) {
		return featureMexicoDao.getMunicipioConteoByName(state+"___");
	}

	@Override
	public String findNameByCveGeo(String cvegeo) {
		return featureMexicoDao.getNameByCveGeo(cvegeo);
	}


}
