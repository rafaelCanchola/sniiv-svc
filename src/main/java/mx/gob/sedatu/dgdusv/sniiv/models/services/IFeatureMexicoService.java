package mx.gob.sedatu.dgdusv.sniiv.models.services;

import mx.gob.sedatu.dgdusv.sniiv.models.entity.FeatureMexico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFeatureMexicoService {

    List<FeatureMexico> findAll();

    FeatureMexico save(FeatureMexico poligono);

    void delete(Long id);

    FeatureMexico findById(Long id);

    Page<FeatureMexico> findAllPageable(Pageable pg);

    Long countAll();

    Long countState();

    Long countMuniFromState(String state);

    String findNameByCveGeo(String cvegeo);
}
