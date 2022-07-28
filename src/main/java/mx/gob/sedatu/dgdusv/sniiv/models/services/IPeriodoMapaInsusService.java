package mx.gob.sedatu.dgdusv.sniiv.models.services;

import mx.gob.sedatu.dgdusv.sniiv.models.entity.PeriodoMapaInsus;

import java.util.List;

public interface IPeriodoMapaInsusService {

    List<PeriodoMapaInsus> findAll();

    PeriodoMapaInsus save(PeriodoMapaInsus yearInsus);

    void delete(Long id);

    PeriodoMapaInsus findById(Long id);

    Integer getLastYear();


}
