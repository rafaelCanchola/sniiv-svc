package mx.gob.sedatu.dgdusv.sniiv.models.services;

import mx.gob.sedatu.dgdusv.sniiv.models.entity.PnvAcciones;

public interface IPnvAccionesService {

    PnvAcciones save(PnvAcciones acciones);

    void delete(Long id);

    PnvAcciones findById(Long id);

    void deleteTrimestre(Integer anio,Integer trimestre);

    PnvAcciones findByInformation(Integer anio,Integer trimestre,Integer objetivo, Integer estatus);

}
