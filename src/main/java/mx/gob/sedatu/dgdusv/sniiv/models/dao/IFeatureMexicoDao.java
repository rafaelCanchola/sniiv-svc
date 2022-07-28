package mx.gob.sedatu.dgdusv.sniiv.models.dao;

import mx.gob.sedatu.dgdusv.sniiv.models.entity.FeatureMexico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IFeatureMexicoDao extends JpaRepository<FeatureMexico,Long> {

    @Query(value="SELECT COUNT(clave_geo) FROM features_mexico WHERE CAST (clave_geo AS INTEGER) <= 32",nativeQuery=true)
    Long getStateConteoByName();

    @Query(value="SELECT COUNT(clave_geo) FROM features_mexico WHERE clave_geo LIKE ?1 ",nativeQuery=true)
    Long getMunicipioConteoByName(String state);

    @Query(value="SELECT nombre FROM features_mexico WHERE clave_geo = ?1 ",nativeQuery=true)
    String getNameByCveGeo(String cvegeo);

}
