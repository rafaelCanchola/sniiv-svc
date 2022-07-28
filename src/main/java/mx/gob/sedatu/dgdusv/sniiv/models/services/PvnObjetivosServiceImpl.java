package mx.gob.sedatu.dgdusv.sniiv.models.services;

import mx.gob.sedatu.dgdusv.sniiv.models.dao.IPnvObjetivosDao;
import mx.gob.sedatu.dgdusv.sniiv.models.entity.PnvObjetivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PvnObjetivosServiceImpl implements IPnvObjetivosService{

    @Autowired
    IPnvObjetivosDao pnvObjetivosDao;

    @Override
    public PnvObjetivos save(PnvObjetivos objetivos) {
        return pnvObjetivosDao.save(objetivos);
    }

    @Override
    public void delete(Long id) {
        pnvObjetivosDao.deleteById(id);
    }

    @Override
    public PnvObjetivos findById(Long id) {
        return pnvObjetivosDao.findById(id).get();
    }

    @Override
    public void deleteTrimestre(Integer anio, Integer trimestre) {
        pnvObjetivosDao.deleteAllByTrimestreAndAnio(trimestre,anio);
    }
}
