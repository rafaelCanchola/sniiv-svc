package mx.gob.sedatu.dgdusv.sniiv.models.services;

import mx.gob.sedatu.dgdusv.sniiv.models.dao.ICuboInsusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CuboInsusServiceImpl implements ICuboInsusService{
    @Autowired
    ICuboInsusDao cuboInsusDao;
    @Override
    @Transactional(readOnly = true)
    public Long getAccionesByPolygon(String polygon, Integer year) { return cuboInsusDao.getAccionesByPolygon(polygon, year); }

    @Override
    @Transactional(readOnly = true)
    public Long getMontosByPolygon(String polygon, Integer year) { return cuboInsusDao.getMontosByPolygon(polygon, year); }

    @Override
    @Transactional(readOnly = true)
    public Long getAccionesByMenPolygon(String polygon, Integer year) { return cuboInsusDao.getAccionesByMenPolygon(polygon, year); }

    @Override
    @Transactional(readOnly = true)
    public Long getAccionesByWomenPolygon(String polygon, Integer year) { return cuboInsusDao.getAccionesByWomenPolygon(polygon, year); }

    @Override
    @Transactional(readOnly = true)
    public Long getMontosByMenPolygon(String polygon, Integer year) { return cuboInsusDao.getMontosByMenPolygon(polygon, year); }

    @Override
    @Transactional(readOnly = true)
    public Long getMontosByWomenPolygon(String polygon, Integer year) { return cuboInsusDao.getMontosByWomenPolygon(polygon, year); }
}
