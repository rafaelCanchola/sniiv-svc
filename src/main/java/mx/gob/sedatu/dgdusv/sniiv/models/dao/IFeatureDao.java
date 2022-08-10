package mx.gob.sedatu.dgdusv.sniiv.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.gob.sedatu.dgdusv.sniiv.models.entity.FeatureInsus;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IFeatureDao extends JpaRepository<FeatureInsus,Long>{
    @Query(value="SELECT SUM(importe_t) AS total FROM features_insus WHERE clave_estado = ?1 AND anio = ?2",nativeQuery=true)
    Long getSumStateByTotal(String state,Integer year);

    @Query(value="SELECT SUM(importe_h) AS total FROM features_insus WHERE clave_estado = ?1 AND anio = ?2",nativeQuery=true)
    Long getSumStateByMen(String state,Integer year);

    @Query(value="SELECT SUM(importe_m) AS total FROM features_insus WHERE clave_estado = ?1 AND anio = ?2",nativeQuery=true)
    Long getSumStateByWomen(String state,Integer year);

    @Query(value="SELECT SUM(importe_t) AS total FROM features_insus WHERE clave_estado = ?1 AND clave_municipio = ?2 AND anio = ?3",nativeQuery=true)
    Long getSumTownByTotal(String state, String muni,Integer year);

    @Query(value="SELECT SUM(importe_h) AS total FROM features_insus WHERE clave_estado = ?1 AND clave_municipio = ?2 AND anio = ?3",nativeQuery=true)
    Long getSumTownByMen(String state, String muni,Integer year);

    @Query(value="SELECT SUM(importe_m) AS total FROM features_insus WHERE clave_estado = ?1 AND clave_municipio = ?2 AND anio = ?3",nativeQuery=true)
    Long getSumTownByWomen(String state, String muni,Integer year);

    @Query(value="SELECT * FROM features_insus WHERE clave_estado = ?1 AND anio = ?2",nativeQuery=true)
    List<FeatureInsus> getAllByState(String state,Integer year);

    @Query(value="SELECT * FROM features_insus WHERE clave_estado = ?1 AND clave_municipio = ?2 AND anio = ?3",nativeQuery=true)
    List<FeatureInsus> getAllByMuni(String state,String muni,Integer year);

    @Query(value="SELECT COUNT(poligono) FROM features_insus WHERE clave_estado = ?1 AND clave_municipio = ?2 AND anio = ?3",nativeQuery=true)
    Long getCountByStateMuni(String state,String muni,Integer year);

    @Query(value="SELECT COUNT(DISTINCT entidad) FROM features_insus WHERE anio = ?1",nativeQuery=true)
    Long getCountOfStates(Integer year);

    @Query(value="SELECT COUNT(DISTINCT municipio) FROM features_insus WHERE clave_estado = ?1  AND anio = ?2",nativeQuery=true)
    Long getCountOfMuniFromState(String state,Integer year);

    @Query(value="SELECT SUM(acciones) FROM features_insus WHERE clave_estado = ?1  AND anio = ?2",nativeQuery=true)
    Long getSumAccionesStateByTotal(String state,Integer year);

    @Query(value="SELECT SUM(h) FROM features_insus WHERE clave_estado = ?1  AND anio = ?2",nativeQuery=true)
    Long getSumAccionesStateByMen(String state,Integer year);

    @Query(value="SELECT SUM(m) FROM features_insus WHERE clave_estado = ?1 AND anio = ?2",nativeQuery=true)
    Long getSumAccionesStateByWomen(String state,Integer year);

    @Query(value="SELECT SUM(acciones) FROM features_insus WHERE clave_estado = ?1 AND clave_municipio = ?2 AND anio = ?3",nativeQuery=true)
    Long getSumAccionesTownByTotal(String state,String muni,Integer year);

    @Query(value="SELECT SUM(h) FROM features_insus WHERE clave_estado = ?1 AND clave_municipio = ?2 AND anio = ?3",nativeQuery=true)
    Long getSumAccionesTownByMen(String state,String muni,Integer year);

    @Query(value="SELECT SUM(m) FROM features_insus WHERE clave_estado = ?1 AND clave_municipio = ?2 AND anio = ?3",nativeQuery=true)
    Long getSumAccionesTownByWomen(String state,String muni,Integer year);

    @Query(value="SELECT * FROM features_insus WHERE poligono LIKE ?1 ",nativeQuery=true)
    FeatureInsus featureExists(String nomPoli);


}
