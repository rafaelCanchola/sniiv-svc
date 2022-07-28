package mx.gob.sedatu.dgdusv.sniiv.models.dao;

import mx.gob.sedatu.dgdusv.sniiv.models.entity.CuboInsus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICuboInsusDao extends JpaRepository<CuboInsus,Long> {

    @Query(value="SELECT SUM(acciones) FROM cubo_insus WHERE poligono = ?1 AND anio = ?2",nativeQuery=true)
    Long getAccionesByPolygon(String polygon,Integer year);

    @Query(value="SELECT SUM(monto) FROM cubo_insus WHERE poligono = ?1 AND anio = ?2",nativeQuery=true)
    Long getMontosByPolygon(String polygon,Integer year);

    @Query(value="SELECT SUM(acciones) FROM cubo_insus WHERE poligono = ?1 AND anio = ?2 AND id_genero = 1",nativeQuery=true)
    Long getAccionesByMenPolygon(String polygon,Integer year);

    @Query(value="SELECT SUM(acciones) FROM cubo_insus WHERE poligono = ?1 AND anio = ?2 AND id_genero = 2",nativeQuery=true)
    Long getAccionesByWomenPolygon(String polygon,Integer year);

    @Query(value="SELECT SUM(monto) FROM cubo_insus WHERE poligono = ?1 AND anio = ?2 AND id_genero = 1",nativeQuery=true)
    Long getMontosByMenPolygon(String polygon,Integer year);

    @Query(value="SELECT SUM(monto) FROM cubo_insus WHERE poligono = ?1 AND anio = ?2 AND id_genero = 2",nativeQuery=true)
    Long getMontosByWomenPolygon(String polygon,Integer year);


}
