package mx.gob.sedatu.dgdusv.sniiv.models.services;

import mx.gob.sedatu.dgdusv.sniiv.models.entity.PnvInformes;

public interface IPnvInformesService {

    PnvInformes save(PnvInformes informe);

    void delete(Long id);

    PnvInformes findById(Long id);

    PnvInformes findByInformation(Integer anio, Integer trimestre);

}
