package mx.gob.sedatu.dgdusv.sniiv.models.services;

import mx.gob.sedatu.dgdusv.sniiv.models.entity.ReporteMensual;

public interface IReporteMensualService {

    ReporteMensual save(ReporteMensual informe);

    void delete(Long id);

    ReporteMensual findById(Long id);

    ReporteMensual findByInformation(Integer anio, Integer trimestre);

}
