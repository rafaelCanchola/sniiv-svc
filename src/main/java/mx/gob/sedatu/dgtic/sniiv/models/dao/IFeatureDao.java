package mx.gob.sedatu.dgtic.sniiv.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.gob.sedatu.dgtic.sniiv.models.entity.Feature;

public interface IFeatureDao extends JpaRepository<Feature,Long>{

}
