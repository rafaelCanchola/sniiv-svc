package mx.gob.sedatu.dgdusv.sniiv.models.dao;

import mx.gob.sedatu.dgdusv.sniiv.models.entity.PeriodoMapaInsus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IPeriodoMapaInsusDao extends JpaRepository<PeriodoMapaInsus,Long> {

    @Query(value="SELECT MAX(anio) FROM c_periodo_mapa_insus",nativeQuery=true)
    Integer getLastYear();

    PeriodoMapaInsus getPeriodoMapaInsusByAnio(Integer year);

}
