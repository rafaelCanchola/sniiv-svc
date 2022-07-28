package mx.gob.sedatu.dgdusv.sniiv.models.services;

import mx.gob.sedatu.dgdusv.sniiv.models.dao.IPeriodoMapaInsusDao;
import mx.gob.sedatu.dgdusv.sniiv.models.entity.PeriodoMapaInsus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodoMapaInsusServiceImpl implements IPeriodoMapaInsusService{
    @Autowired
    private IPeriodoMapaInsusDao periodoMapaInsusDao;

    @Override
    public List<PeriodoMapaInsus> findAll() {
        return periodoMapaInsusDao.findAll();
    }

    @Override
    public PeriodoMapaInsus save(PeriodoMapaInsus yearInsus) {
        return periodoMapaInsusDao.save(yearInsus);
    }

    @Override
    public void delete(Long id) {
        periodoMapaInsusDao.deleteById(id);
    }

    @Override
    public PeriodoMapaInsus findById(Long id) {
        return periodoMapaInsusDao.findById(id).get();
    }

    @Override
    public Integer getLastYear() {
        return periodoMapaInsusDao.getLastYear();
    }
}
