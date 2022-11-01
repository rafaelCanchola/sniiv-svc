package mx.gob.sedatu.dgdusv.sniiv.models.services;

import mx.gob.sedatu.dgdusv.sniiv.models.dao.IReporteMensualDao;
import mx.gob.sedatu.dgdusv.sniiv.models.entity.ReporteMensual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReporteMensualServiceImpl implements IReporteMensualService{

    @Autowired
    IReporteMensualDao reporteMensualDao;

    @Override
    public ReporteMensual save(ReporteMensual acciones) {
        return reporteMensualDao.save(acciones);
    }

    @Override
    public void delete(Long id) {
        reporteMensualDao.deleteById(id);
    }
    @Override
    public ReporteMensual findById(Long id) {
        return reporteMensualDao.findById(id).get();
    }

    @Override
    public ReporteMensual findByInformation(Integer anio, Integer mes){
        return reporteMensualDao.findReporteMensualByAnioAndMes(anio, mes);
    }
}
