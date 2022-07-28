package mx.gob.sedatu.dgdusv.sniiv.models.dao;

import mx.gob.sedatu.dgdusv.sniiv.models.entity.PnvAcciones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPnvAccionesDao extends JpaRepository<PnvAcciones,Long> {

   void deleteAllByTrimestreAndAnio(Integer trimestre, Integer anio);

   PnvAcciones findPnvAccionesByAnioAndTrimestreAndObjetivoAndEstatus(Integer anio,Integer trimestre,Integer objetivo, Integer estatus);

}
