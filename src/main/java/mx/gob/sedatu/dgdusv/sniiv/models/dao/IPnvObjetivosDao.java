package mx.gob.sedatu.dgdusv.sniiv.models.dao;

import mx.gob.sedatu.dgdusv.sniiv.models.entity.PnvObjetivos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPnvObjetivosDao extends JpaRepository<PnvObjetivos,Long> {

    void deleteAllByTrimestreAndAnio(Integer trimestre, Integer anio);
}
