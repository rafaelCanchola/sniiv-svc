package mx.gob.sedatu.dgdusv.sniiv.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class PoligonoHash {

	@Setter @Getter
	private String alias;
	
	@Setter @Getter
	private Object value; 

}
