package mx.gob.sedatu.dgdusv.sniiv.models.services;

import mx.gob.sedatu.dgdusv.sniiv.models.dao.IPnvAccionesDao;
import mx.gob.sedatu.dgdusv.sniiv.models.entity.PnvAcciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PnvAccionesServiceImpl implements IPnvAccionesService{

    @Autowired
    IPnvAccionesDao pnvAccionesDao;

    @Override
    public PnvAcciones save(PnvAcciones acciones) {
        return pnvAccionesDao.save(acciones);
    }

    @Override
    public void delete(Long id) {
        pnvAccionesDao.deleteById(id);
    }

    @Override
    public PnvAcciones findById(Long id) {
        return pnvAccionesDao.findById(id).get();
    }

    @Override
    public void deleteTrimestre(Integer anio, Integer trimestre) {
        pnvAccionesDao.deleteAllByTrimestreAndAnio(trimestre,anio);
    }

    @Override
    public PnvAcciones findByInformation(Integer anio, Integer trimestre, Integer objetivo, Integer estatus) {
        return pnvAccionesDao.findPnvAccionesByAnioAndTrimestreAndObjetivoAndEstatus(anio, trimestre, objetivo, estatus);
    }

}
