package mx.gob.sedatu.dgdusv.sniiv.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="pnv_objetivos")
public class PnvObjetivos implements Serializable {

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
    private Integer tipo_objetivo;
    @Column(columnDefinition="text")
    @Setter @Getter
    private String organismo;
    @Setter @Getter
    private Integer total;
    @Setter @Getter
    private Integer concluida;
    @Setter @Getter
    private Integer en_proceso;
    @Setter @Getter
    private Integer sin_realizar;
    @Setter @Getter
    private Integer por_iniciar;

}
