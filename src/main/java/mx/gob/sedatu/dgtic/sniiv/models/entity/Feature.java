package mx.gob.sedatu.dgtic.sniiv.models.entity;

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
import mx.gob.sedatu.dgtic.sniiv.models.dto.PoligonoHash;

@Entity
@Table(name="features")
public class Feature implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Setter @Getter
	private Long id;
	
	@Column(columnDefinition="text")
	@Setter @Getter
	private String cvegeo;
	
	@Setter @Getter
	private Long ent;
	
	@Setter @Getter
	private Long mun;
	
	@Setter @Getter
	private Long loc;
	
	@Setter @Getter
	private Long ageb;
	
	@Setter @Getter
	private Long mza;
	
	@Column(columnDefinition="text")
	@Setter @Getter
	private String ambito;
	
	@Column(columnDefinition="text")
	@Setter @Getter
	private String tipomza;
	
	@Column(columnDefinition="text")
	@Setter @Getter
	private String noment;
	
	@Column(columnDefinition="text")
	@Setter @Getter
	private String nommun;
	
	@Column(columnDefinition="text")
	@Setter @Getter
	private String nomloc;
	
	@Setter @Getter
	private Long pobtot;
	
	@Setter @Getter
	private Long pobfem;
	
	@Setter @Getter
	private Long pobmas;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="poligono_id")
	@Setter @Getter
	private Poligono poligono;
	
	/*
	@Setter @Getter 
	private Long p3ym_hli;	
	@Setter @Getter 
	private Long p3hlinhe;	
	@Setter @Getter 
	private Long p5_hli;	
	@Setter @Getter 
	private Long phog_ind;	
	@Setter @Getter 
	private Long pob_afro;	
	@Setter @Getter 
	private Long pob_afro_f;	
	@Setter @Getter 
	private Long pob_afro_m;	
	@Setter @Getter 
	private Long pcon_disc;	
	@Setter @Getter 
	private Long pcdisc_mot;	
	@Setter @Getter 
	private Long pcdisc_vis;	
	@Setter @Getter 
	private Long pcdisc_len;	
	@Setter @Getter 
	private Long pcdisc_aud;	
	@Setter @Getter 
	private Long pcdisc_m_1;	
	@Setter @Getter 
	private Long pcdisc_men;	
	@Setter @Getter 
	private Long pcon_limi;	
	@Setter @Getter 
	private Long pclim_csb;	
	@Setter @Getter 
	private Long pclim_vis;	
	@Setter @Getter 
	private Long pclim_haco;	
	@Setter @Getter 
	private Long pclim_oaud;	
	@Setter @Getter 
	private Long pclim_mot2;	
	@Setter @Getter 
	private Long pclim_re_c;	
	@Setter @Getter 
	private Long pclim_pmen;	
	@Setter @Getter 
	private Long psind_lim;	
	@Setter @Getter 
	private Long p3a5_noa;	
	@Setter @Getter 
	private Long p3a5_noa_f;	
	@Setter @Getter 
	private Long p3a5_noa_m;	
	@Setter @Getter 
	private Long p6a11_noa;	
	@Setter @Getter 
	private Long p6a11_noaf;	
	@Setter @Getter 
	private Long p6a11_noam;	
	@Setter @Getter 
	private Long p12a14noa;	
	@Setter @Getter 
	private Long p12a14noaf;	
	@Setter @Getter 
	private Long p12a14noam;	
	@Setter @Getter 
	private Long p15a17a;	
	@Setter @Getter 
	private Long p15a17a_f;	
	@Setter @Getter 
	private Long p15a17a_m;	
	@Setter @Getter 
	private Long p18a24a;	
	@Setter @Getter 
	private Long p18a24a_f;	
	@Setter @Getter 
	private Long p18a24a_m;	
	@Setter @Getter 
	private Long p8a14an;	
	@Setter @Getter 
	private Long p8a14an_f;	
	@Setter @Getter 
	private Long p8a14an_m;	
	@Setter @Getter 
	private Long p15ym_an;	
	@Setter @Getter 
	private Long p15ym_an_f;	
	@Setter @Getter 
	private Long p15ym_an_m;	
	@Setter @Getter 
	private Long p15ym_se;	
	@Setter @Getter 
	private Long p15ym_se_f;	
	@Setter @Getter 
	private Long p15ym_se_m;	
	@Setter @Getter 
	private Long p15pri_in;	
	@Setter @Getter 
	private Long p15pri_inf;	
	@Setter @Getter 
	private Long p15pri_inm;	
	@Setter @Getter 
	private Long p15pri_co;	
	@Setter @Getter 
	private Long p15pri_cof;	
	@Setter @Getter 
	private Long p15pri_com;	
	@Setter @Getter 
	private Long p15sec_in;	
	@Setter @Getter 
	private Long p15sec_inf;	
	@Setter @Getter 
	private Long p15sec_inm;	
	@Setter @Getter 
	private Long p15sec_co;	
	@Setter @Getter 
	private Long p15sec_cof;	
	@Setter @Getter 
	private Long p15sec_com;	
	@Setter @Getter 
	private Long p18ym_pb;	
	@Setter @Getter 
	private Long p18ym_pb_f;	
	@Setter @Getter 
	private Long p18ym_pb_m;	
	@Setter @Getter 
	private Double graproes;	
	@Setter @Getter 
	private Double graproes_f;	
	@Setter @Getter 
	private Double graproes_m;	
	@Setter @Getter 
	private Long pea;	
	@Setter @Getter 
	private Long pea_f;	
	@Setter @Getter
	private Long pea_m;	
	@Setter @Getter 
	private Long pe_inac;	
	@Setter @Getter 
	private Long pe_inac_f;	
	@Setter @Getter 
	private Long pe_inac_m;	
	@Setter @Getter 
	private Long pocupada;	
	@Setter @Getter 
	private Long pocupada_f;	
	@Setter @Getter 
	private Long pocupada_m;	
	@Setter @Getter 
	private Long pdesocup;	
	@Setter @Getter 
	private Long pdesocup_f;	
	@Setter @Getter 
	private Long pdesocup_m;	
	@Setter @Getter 
	private Long psinder;	
	@Setter @Getter 
	private Long pder_ss;	
	@Setter @Getter 
	private Long pder_imss;	
	@Setter @Getter 
	private Long pder_iste;	
	@Setter @Getter 
	private Long pder_istee;	
	@Setter @Getter 
	private Long pafil_pdom;	
	@Setter @Getter 
	private Long pder_segp;	
	@Setter @Getter 
	private Long pder_imssb;	
	@Setter @Getter 
	private Long pafil_ipri;	
	@Setter @Getter 
	private Long pafil_otra;	
	@Setter @Getter 
	private Long p12ym_solt;	
	@Setter @Getter 
	private Long p12ym_casa;	
	@Setter @Getter 
	private Long p12ym_sepa;	
	@Setter @Getter 
	private Long pcatolica;	
	@Setter @Getter 
	private Long pro_crieva;	
	@Setter @Getter 
	private Long potras_rel;	
	@Setter @Getter 
	private Long psin_relig;	
	@Setter @Getter 
	private Long tothog;	
	@Setter @Getter 
	private Long hogjef_f;	
	@Setter @Getter 
	private Long hogjef_m;	
	@Setter @Getter 
	private Long pobhog;	
	@Setter @Getter 
	private Long phogjef_f;
	@Setter @Getter 
	private Long phogjef_m;	
	@Setter @Getter 
	private Long vivtot;	
	@Setter @Getter 
	private Long tvivhab;	
	@Setter @Getter 
	private Long tvivpar;	
	@Setter @Getter 
	private Long vivpar_hab;	
	@Setter @Getter 
	private Long vivparh_cv;	
	@Setter @Getter 
	private Long tvivparhab;	
	@Setter @Getter 
	private Long vivpar_des;	
	@Setter @Getter 
	private Long vivpar_ut;	
	@Setter @Getter 
	private Long ocupvivpar;
	@Setter @Getter 
	private Double prom_ocup;	
	@Setter @Getter 
	private Double pro_ocup_c;	
	@Setter @Getter 
	private Long vph_pisodt;	
	@Setter @Getter 
	private Long vph_pisoti;	
	@Setter @Getter 
	private Long vph_1dor;	
	@Setter @Getter 
	private Long vph_2ymasd;	
	@Setter @Getter 
	private Long vph_1cuart;	
	@Setter @Getter 
	private Long vph_2cuart;	
	@Setter @Getter 
	private Long vph_3ymasc;	
	@Setter @Getter 
	private Long vph_c_elec;	
	@Setter @Getter 
	private Long vph_s_elec;	
	@Setter @Getter 
	private Long vph_aguadv;	
	@Setter @Getter 
	private Long vph_aeasp;	
	@Setter @Getter 
	private Long vph_aguafv;	
	@Setter @Getter 
	private Long vph_tinaco;	
	@Setter @Getter 
	private Long vph_cister;	
	@Setter @Getter 
	private Long vph_excsa;	
	@Setter @Getter 
	private Long vph_letr;	
	@Setter @Getter 
	private Long vph_drenaj;	
	@Setter @Getter 
	private Long vph_nodren;	
	@Setter @Getter 
	private Long vph_c_serv;	
	@Setter @Getter 
	private Long vph_ndeaed;	
	@Setter @Getter 
	private Long vph_dsadma;	
	@Setter @Getter 
	private Long vph_ndacmm;	
	@Setter @Getter 
	private Long vph_snbien;	
	@Setter @Getter 
	private Long vph_refri;	
	@Setter @Getter 
	private Long vph_lavad;	
	@Setter @Getter 
	private Long vph_hmicro;	
	@Setter @Getter 
	private Long vph_autom;	
	@Setter @Getter 
	private Long vph_moto;	
	@Setter @Getter 
	private Long vph_bici;	
	@Setter @Getter 
	private Long vph_radio;	
	@Setter @Getter 
	private Long vph_tv;	
	@Setter @Getter 
	private Long vph_pc;	
	@Setter @Getter 
	private Long vph_telef;	
	@Setter @Getter 
	private Long vph_cel;	
	@Setter @Getter 
	private Long vph_inter;	
	@Setter @Getter 
	private Long vph_stvp;	
	@Setter @Getter 
	private Long vph_spmvpi;	
	@Setter @Getter 
	private Long vph_cvj;	
	@Setter @Getter 
	private Long vph_sinrtv;	
	@Setter @Getter 
	private Long vph_sinltc;	
	@Setter @Getter 
	private Long vph_sincin;	
	@Setter @Getter 
	private Long vph_sintic;	
	*/
	@Setter @Getter 
	private Double area;	
	@Setter @Getter 
	private Double dens_pob;	
	@Setter @Getter 
	private Double area_ha;
	@Setter @Getter 
	private Double dens_ha;
	

	public List<PoligonoHash> toListHash() {
        List<PoligonoHash> puntos = new ArrayList<>();
        puntos.add(new PoligonoHash("Folio", this.getCvegeo()));
        puntos.add(new PoligonoHash("Entidad", this.getNoment()));
        puntos.add(new PoligonoHash("Municipio", this.getNommun()));
        puntos.add(new PoligonoHash("Localidad", this.getNomloc()));
        puntos.add(new PoligonoHash("Ambito", this.getAmbito()));
        puntos.add(new PoligonoHash("Tipo Manzana", this.getTipomza()));
        puntos.add(new PoligonoHash("Población total", this.getPobtot()));
        puntos.add(new PoligonoHash("Mujeres", this.getPobfem()));
        puntos.add(new PoligonoHash("Hombres", this.getPobmas()));
        puntos.add(new PoligonoHash("Densidad Poblacional", this.getDens_ha()));
        puntos.add(new PoligonoHash("Area Habitacional", this.getArea_ha()));
        puntos.add(new PoligonoHash("Densidad Habitacional", this.getDens_pob()));
        return puntos;
    }
	
	
	

}
