package mx.gob.sedatu.dgdusv.sniiv.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="reporte_mensual")
public class ReporteMensual implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @Setter @Getter
    private Integer anio;
    @Setter @Getter
    private Integer mes;
    @Column(columnDefinition="text")
    @Setter @Getter
    private String url;
}
