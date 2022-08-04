package mx.gob.sedatu.dgdusv.sniiv.models.dao;

import mx.gob.sedatu.dgdusv.sniiv.models.entity.PnvInformes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPnvInformesDao extends JpaRepository<PnvInformes,Long> {

    PnvInformes findPnvInformesByAnioAndTrimestre(Integer anio, Integer trimestre);

}
