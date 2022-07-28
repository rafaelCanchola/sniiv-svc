package mx.gob.sedatu.dgdusv.sniiv.models.entity;

import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="cubo_insus")
public class CuboInsus implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Setter @Getter
	private Long id;

	@Setter @Getter
	private Integer anio;
	@Setter @Getter
	private Integer mes;
	@Column(columnDefinition="text")
	@Setter @Getter
	private String clave_estado;
	@Column(columnDefinition="text")
	@Setter @Getter
	private String clave_municipio;
	@Column(columnDefinition="text")
	@Setter @Getter
	private String poligono;
	@Setter @Getter
	private Integer id_rango_edad;
	@Setter @Getter
	private Integer id_genero;
	@Setter @Getter
	private Integer id_escolaridad;
	@Setter @Getter
	private Integer id_estado_civil;
	@Column(columnDefinition="text")
	@Setter @Getter
	private String discapacidad;
	@Column(columnDefinition="text")
	@Setter @Getter
	private String indigena;
	@Column(columnDefinition="text")
	@Setter @Getter
	private String alfabetismo;
	@Column(columnDefinition="text")
	@Setter @Getter
	private String desalojos;
	@Column(columnDefinition="text")
	@Setter @Getter
	private String pavimentacion;
	@Column(columnDefinition="text")
	@Setter @Getter
	private String alumbrado;
	@Column(columnDefinition="text")
	@Setter @Getter
	private String transporte;
	@Setter @Getter
	private Integer integrantes;
	@Setter @Getter
	private Integer cuartos;
	@Setter @Getter
	private Double monto;
	@Setter @Getter
	private Integer acciones;

}
