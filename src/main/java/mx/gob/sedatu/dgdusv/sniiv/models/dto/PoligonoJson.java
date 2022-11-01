package mx.gob.sedatu.dgdusv.sniiv.models.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class PoligonoJson implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Setter @Getter
	private Long id;
	
	@Setter @Getter
    private String the_geom;
	
	@Setter @Getter
	private String cvegeo;

	@Setter @Getter
	private Long importe_h;

	@Setter @Getter
	private Long importe_m;

	@Setter @Getter
	private Long importe_t;

	@Setter @Getter
	private Long max;

	@Setter @Getter
	private Long min;

}
