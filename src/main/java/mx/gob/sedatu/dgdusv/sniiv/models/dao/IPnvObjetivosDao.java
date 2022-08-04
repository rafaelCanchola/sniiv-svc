package mx.gob.sedatu.dgdusv.sniiv.models.dao;

import mx.gob.sedatu.dgdusv.sniiv.models.entity.PnvObjetivos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPnvObjetivosDao extends JpaRepository<PnvObjetivos,Long> {

    void deleteAllByTrimestreAndAnio(Integer trimestre, Integer anio);

    @Query(value="SELECT * FROM pnv_objetivos WHERE anio = ?1 AND trimestre = ?2 AND organismo LIKE ?3 AND tipo_objetivo = ?4 ",nativeQuery=true)
    PnvObjetivos findPnvObjetivosByAnioAndTrimestreAndOrganismoAndTipo_objetivo(Integer anio, Integer trimestre, String organismo,Integer tipo_objetivo);

}
