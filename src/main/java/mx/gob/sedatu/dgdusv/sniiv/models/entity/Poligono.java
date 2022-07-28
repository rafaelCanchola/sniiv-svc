package mx.gob.sedatu.dgdusv.sniiv.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.locationtech.jts.geom.Geometry;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="poligonos")
public class Poligono implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Setter @Getter
	private Long id;
	
	@Setter @Getter
    private Geometry the_geom;

}
