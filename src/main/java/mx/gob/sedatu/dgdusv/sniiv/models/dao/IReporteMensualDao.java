package mx.gob.sedatu.dgdusv.sniiv.models.dao;

import mx.gob.sedatu.dgdusv.sniiv.models.entity.ReporteMensual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReporteMensualDao extends JpaRepository<ReporteMensual,Long> {

    ReporteMensual findReporteMensualByAnioAndMes(Integer anio, Integer mes);

}
