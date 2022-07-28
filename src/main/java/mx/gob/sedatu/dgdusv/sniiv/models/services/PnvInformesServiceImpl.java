package mx.gob.sedatu.dgdusv.sniiv.models.services;

import mx.gob.sedatu.dgdusv.sniiv.models.dao.IPnvInformesDao;
import mx.gob.sedatu.dgdusv.sniiv.models.entity.PnvInformes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PnvInformesServiceImpl implements IPnvInformesService{

    @Autowired
    IPnvInformesDao pnvInformesDao;

    @Override
    public PnvInformes save(PnvInformes acciones) {
        return pnvInformesDao.save(acciones);
    }

    @Override
    public void delete(Long id) {
        pnvInformesDao.deleteById(id);
    }
    @Override
    public PnvInformes findById(Long id) {
        return pnvInformesDao.findById(id).get();
    }
}
