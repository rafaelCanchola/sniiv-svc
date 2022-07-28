package mx.gob.sedatu.dgdusv.sniiv.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import mx.gob.sedatu.dgdusv.sniiv.models.dto.PoligonoHash;

@Entity
@Table(name="features_insus")
public class FeatureInsus implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Setter @Getter
	private Long id;
	
	@Column(columnDefinition="text")
	@Setter @Getter
	private String clave_estado;

	@Column(columnDefinition="text")
	@Setter @Getter
	private String clave_municipio;

	@Column(columnDefinition="text")
	@Setter @Getter
	private String clave_localidad;

	@Column(columnDefinition="text")
	@Setter @Getter
	private String entidad;

	@Column(columnDefinition="text")
	@Setter @Getter
	private String municipio;

	@Column(columnDefinition="text")
	@Setter @Getter
	private String localidad;

	@Column(columnDefinition="text")
	@Setter @Getter
	private String poligono;
	
	@Setter @Getter
	private Integer anio;

	@Setter @Getter
	private Long acciones;

	@Setter @Getter
	private Long importe_h;

	@Setter @Getter
	private Long h;

	@Setter @Getter
	private Long importe_m;

	@Setter @Getter
	private Long m;

	@Setter @Getter
	private Long importe_t;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="poligono_id")
	@Setter @Getter
	private Poligono pol;

	public List<PoligonoHash> toListHash() {
        List<PoligonoHash> puntos = new ArrayList<>();
        puntos.add(new PoligonoHash("Polígono", this.getPoligono()));
        puntos.add(new PoligonoHash("Entidad", this.getEntidad()));
        puntos.add(new PoligonoHash("Municipio", this.getMunicipio()));
        puntos.add(new PoligonoHash("Localidad", this.getLocalidad()));
        puntos.add(new PoligonoHash("Año", this.getAnio()));
        puntos.add(new PoligonoHash("Monto por Hombres", this.getImporte_h()));
		puntos.add(new PoligonoHash("Acciones por hombres", this.getH()));
		puntos.add(new PoligonoHash("Monto por Mujeres", this.getImporte_m()));
		puntos.add(new PoligonoHash("Acciones por Mujeres", this.getM()));
		puntos.add(new PoligonoHash("Monto Total", this.getImporte_t()));
        puntos.add(new PoligonoHash("Acciones", this.getAcciones()));
        return puntos;
    }
	
	
	

}
