package mx.gob.sedatu.dgdusv.sniiv.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="pnv_acciones")
public class PnvAcciones implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @Setter @Getter
    private Integer anio;
    @Setter @Getter
    private Integer trimestre;
    @Setter @Getter
    private Integer objetivo;
    @Setter @Getter
    private Integer total_compartidas;
    @Setter @Getter
    private Integer estatus;

}
