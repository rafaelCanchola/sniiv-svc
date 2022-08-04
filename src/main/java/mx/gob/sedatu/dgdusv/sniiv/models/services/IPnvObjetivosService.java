package mx.gob.sedatu.dgdusv.sniiv.models.services;

import mx.gob.sedatu.dgdusv.sniiv.models.entity.PnvObjetivos;

public interface IPnvObjetivosService {

    PnvObjetivos save(PnvObjetivos objetivos);

    void delete(Long id);

    PnvObjetivos findById(Long id);

    void deleteTrimestre(Integer anio, Integer trimestre);

    PnvObjetivos findByInformation(Integer anio, Integer trimestre, Integer objetivo, String organismo);
}
