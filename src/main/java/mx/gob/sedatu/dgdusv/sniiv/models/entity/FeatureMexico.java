package mx.gob.sedatu.dgdusv.sniiv.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import mx.gob.sedatu.dgdusv.sniiv.models.dto.PoligonoHash;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="features_mexico")
public class FeatureMexico implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Setter @Getter
	private Long id;
	
	@Column(columnDefinition="text")
	@Setter @Getter
	private String clave_geo;

	@Column(columnDefinition="text")
	@Setter @Getter
	private String nombre;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="poligono_id")
	@Setter @Getter
	private Poligono pol;

}
