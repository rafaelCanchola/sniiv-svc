package mx.gob.sedatu.dgtic.sniiv.utilities;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.locationtech.jts.geom.Geometry;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;


import mx.gob.sedatu.dgtic.sniiv.models.entity.Feature;
import mx.gob.sedatu.dgtic.sniiv.models.entity.Poligono;

public class ShapeFileUtils {
	public static List<Feature> readShapeIntoObject(List<String> shapesNames) throws IOException{
		List<Feature> poligonoIterator = new ArrayList<>();
        
		for (String shapeName : shapesNames) {
        	URL shapeURL = new File(shapeName).toURI().toURL();
        	Map<String, Serializable> params = new HashMap<>();
            params.put("url", shapeURL);
            
            DataStore dataStore = DataStoreFinder.getDataStore(params);
            String typeName = dataStore.getTypeNames()[0];
        	FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore.getFeatureSource(typeName);
        	
            FeatureCollection<SimpleFeatureType, SimpleFeature> featureCollection = source.getFeatures();
            FeatureIterator<SimpleFeature> featureIterator = featureCollection.features();
            
            while (featureIterator.hasNext()) {
            	SimpleFeature feature = featureIterator.next();
                Feature shape = new Feature();
                
                shape.setCvegeo(new String(((String)feature.getAttribute("CVEGEO")).getBytes(),StandardCharsets.UTF_8));
                shape.setAmbito(new String(((String)feature.getAttribute("AMBITO")).getBytes(),StandardCharsets.UTF_8));
                shape.setTipomza(new String(((String)feature.getAttribute("TIPOMZA")).getBytes(),StandardCharsets.UTF_8));
                shape.setNoment(new String(((String)feature.getAttribute("Nom_Ent")).getBytes(),StandardCharsets.UTF_8));
                shape.setNommun(new String(((String)feature.getAttribute("Nom_Mun")).getBytes(),StandardCharsets.UTF_8));
                shape.setNomloc(new String(((String)feature.getAttribute("Nom_Loc")).getBytes(),StandardCharsets.UTF_8));
                
                shape.setEnt((Long)feature.getAttribute("ENTIDAD"));
                shape.setMun((Long)feature.getAttribute("MUN"));
                shape.setLoc((Long)feature.getAttribute("LOC"));
                shape.setAgeb((Long)feature.getAttribute("AGEB"));
                shape.setMza((Long)feature.getAttribute("MZA"));
                
                shape.setPobtot((Long)feature.getAttribute("POBTOT"));
                shape.setPobfem((Long)feature.getAttribute("POBFEM"));
                shape.setPobmas((Long)feature.getAttribute("POBMAS"));
                
                /*shape.setP3ym_hli((Long)feature.getAttribute("P3YM_HLI"));	
                shape.setP3hlinhe((Long)feature.getAttribute("P3HLINHE"));	
                shape.setP5_hli((Long)feature.getAttribute("P5_HLI"));	
                shape.setPhog_ind((Long)feature.getAttribute("PHOG_IND"));	
                shape.setPob_afro((Long)feature.getAttribute("POB_AFRO"));	
                shape.setPob_afro_f((Long)feature.getAttribute("POB_AFRO_F"));	
                shape.setPob_afro_m((Long)feature.getAttribute("POB_AFRO_M"));	
                shape.setPcon_disc((Long)feature.getAttribute("PCON_DISC"));	
                shape.setPcdisc_mot((Long)feature.getAttribute("PCDISC_MOT"));	
                shape.setPcdisc_vis((Long)feature.getAttribute("PCDISC_VIS"));	
                shape.setPcdisc_len((Long)feature.getAttribute("PCDISC_LEN"));	
                shape.setPcdisc_aud((Long)feature.getAttribute("PCDISC_AUD"));	
                shape.setPcdisc_m_1((Long)feature.getAttribute("PCDISC_M_1"));	
                shape.setPcdisc_men((Long)feature.getAttribute("PCDISC_MEN"));	
                shape.setPcon_limi((Long)feature.getAttribute("PCON_LIMI"));	
                shape.setPclim_csb((Long)feature.getAttribute("PCLIM_CSB"));	
                shape.setPclim_vis((Long)feature.getAttribute("PCLIM_VIS"));	
                shape.setPclim_haco((Long)feature.getAttribute("PCLIM_HACO"));	
                shape.setPclim_oaud((Long)feature.getAttribute("PCLIM_OAUD"));	
                shape.setPclim_mot2((Long)feature.getAttribute("PCLIM_MOT2"));
                shape.setPclim_re_c((Long)feature.getAttribute("PCLIM_RE_C"));
                shape.setPclim_pmen((Long)feature.getAttribute("PCLIM_PMEN"));	
                shape.setPsind_lim((Long)feature.getAttribute("PSIND_LIM"));	
                shape.setP3a5_noa((Long)feature.getAttribute("P3A5_NOA"));	
                shape.setP3a5_noa_f((Long)feature.getAttribute("P3A5_NOA_F"));	
                shape.setP3a5_noa_m((Long)feature.getAttribute("P3A5_NOA_M"));	
                shape.setP6a11_noa((Long)feature.getAttribute("P6A11_NOA"));	
                shape.setP6a11_noaf((Long)feature.getAttribute("P6A11_NOAF"));	
                shape.setP6a11_noam((Long)feature.getAttribute("P6A11_NOAM"));
                shape.setP12a14noa((Long)feature.getAttribute("P12A14NOA"));
                shape.setP12a14noaf((Long)feature.getAttribute("P12A14NOAF"));	
                shape.setP12a14noam((Long)feature.getAttribute("P12A14NOAM"));	
                shape.setP15a17a((Long)feature.getAttribute("P15A17A"));
                shape.setP15a17a_f((Long)feature.getAttribute("P15A17A_F"));
                shape.setP15a17a_m((Long)feature.getAttribute("P15A17A_M"));	
                shape.setP18a24a((Long)feature.getAttribute("P18A24A"));
                shape.setP18a24a_f((Long)feature.getAttribute("P18A24A_F"));	
                shape.setP18a24a_m((Long)feature.getAttribute("P18A24A_M"));	
                shape.setP8a14an((Long)feature.getAttribute("P8A14AN"));	
                shape.setP8a14an_f((Long)feature.getAttribute("P8A14AN_F"));	
                shape.setP8a14an_m((Long)feature.getAttribute("P8A14AN_M"));
                shape.setP15ym_an((Long)feature.getAttribute("P15YM_AN"));	
                shape.setP15ym_an_f((Long)feature.getAttribute("P15YM_AN_F"));	
                shape.setP15ym_an_m((Long)feature.getAttribute("P15YM_AN_M"));	
                shape.setP15ym_se((Long)feature.getAttribute("P15YM_SE"));	
                shape.setP15ym_se_f((Long)feature.getAttribute("P15YM_SE_F"));*/
                //(Long)feature.getAttribute("P15YM_SE_M"));	(Long)feature.getAttribute("P15PRI_IN"));	(Long)feature.getAttribute("P15PRI_INF"));	(Long)feature.getAttribute("P15PRI_INM"));	(Long)feature.getAttribute("P15PRI_CO"));	(Long)feature.getAttribute("P15PRI_COF"));	(Long)feature.getAttribute("P15PRI_COM"));	(Long)feature.getAttribute("P15SEC_IN"));	(Long)feature.getAttribute("P15SEC_INF"));	(Long)feature.getAttribute("P15SEC_INM"));	(Long)feature.getAttribute("P15SEC_CO"));	(Long)feature.getAttribute("P15SEC_COF"));	(Long)feature.getAttribute("P15SEC_COM"));	(Long)feature.getAttribute("P18YM_PB"));	(Long)feature.getAttribute("P18YM_PB_F"));	(Long)feature.getAttribute("P18YM_PB_M"));	(Double)feature.getAttribute("GRAPROES"));	(Double)feature.getAttribute("GRAPROES_F"));	(Double)feature.getAttribute("GRAPROES_M"));	(Long)feature.getAttribute("PEA"));	(Long)feature.getAttribute("PEA_F"));	(Long)feature.getAttribute("PEA_M"));	(Long)feature.getAttribute("PE_INAC"));	(Long)feature.getAttribute("PE_INAC_F"));	(Long)feature.getAttribute("PE_INAC_M"));	(Long)feature.getAttribute("POCUPADA"));	(Long)feature.getAttribute("POCUPADA_F"));	(Long)feature.getAttribute("POCUPADA_M"));	(Long)feature.getAttribute("PDESOCUP"));	(Long)feature.getAttribute("PDESOCUP_F"));	(Long)feature.getAttribute("PDESOCUP_M"));	(Long)feature.getAttribute("PSINDER"));	(Long)feature.getAttribute("PDER_SS"));	(Long)feature.getAttribute("PDER_IMSS"));	(Long)feature.getAttribute("PDER_ISTE"));	(Long)feature.getAttribute("PDER_ISTEE"));	(Long)feature.getAttribute("PAFIL_PDOM"));	(Long)feature.getAttribute("PDER_SEGP"));	(Long)feature.getAttribute("PDER_IMSSB"));	(Long)feature.getAttribute("PAFIL_IPRI"));	(Long)feature.getAttribute("PAFIL_OTRA"));	(Long)feature.getAttribute("P12YM_SOLT"));	(Long)feature.getAttribute("P12YM_CASA"));	(Long)feature.getAttribute("P12YM_SEPA"));	(Long)feature.getAttribute("PCATOLICA"));	(Long)feature.getAttribute("PRO_CRIEVA"));	(Long)feature.getAttribute("POTRAS_REL"));	(Long)feature.getAttribute("PSIN_RELIG"));	(Long)feature.getAttribute("TOTHOG"));	(Long)feature.getAttribute("HOGJEF_F"));	(Long)feature.getAttribute("HOGJEF_M"));	(Long)feature.getAttribute("POBHOG"));	(Long)feature.getAttribute("PHOGJEF_F"));	(Long)feature.getAttribute("PHOGJEF_M"));	(Long)feature.getAttribute("VIVTOT"));	(Long)feature.getAttribute("TVIVHAB"));	(Long)feature.getAttribute("TVIVPAR"));	(Long)feature.getAttribute("VIVPAR_HAB"));	(Long)feature.getAttribute("VIVPARH_CV"));	(Long)feature.getAttribute("TVIVPARHAB"));	(Long)feature.getAttribute("VIVPAR_DES"));	(Long)feature.getAttribute("VIVPAR_UT"));	(Long)feature.getAttribute("OCUPVIVPAR"));	(Double)feature.getAttribute("PROM_OCUP"));	(Double)feature.getAttribute("PRO_OCUP_C"));	(Long)feature.getAttribute("VPH_PISODT"));	(Long)feature.getAttribute("VPH_PISOTI"));	(Long)feature.getAttribute("VPH_1DOR"));	(Long)feature.getAttribute("VPH_2YMASD"));	(Long)feature.getAttribute("VPH_1CUART"));	(Long)feature.getAttribute("VPH_2CUART"));	(Long)feature.getAttribute("VPH_3YMASC"));	(Long)feature.getAttribute("VPH_C_ELEC"));	(Long)feature.getAttribute("VPH_S_ELEC"));	(Long)feature.getAttribute("VPH_AGUADV"));	(Long)feature.getAttribute("VPH_AEASP"));	(Long)feature.getAttribute("VPH_AGUAFV"));	(Long)feature.getAttribute("VPH_TINACO"));	(Long)feature.getAttribute("VPH_CISTER"));	(Long)feature.getAttribute("VPH_EXCSA"));	(Long)feature.getAttribute("VPH_LETR"));	(Long)feature.getAttribute("VPH_DRENAJ"));	(Long)feature.getAttribute("VPH_NODREN"));	(Long)feature.getAttribute("VPH_C_SERV"));	(Long)feature.getAttribute("VPH_NDEAED"));	(Long)feature.getAttribute("VPH_DSADMA"));	(Long)feature.getAttribute("VPH_NDACMM"));	(Long)feature.getAttribute("VPH_SNBIEN"));	(Long)feature.getAttribute("VPH_REFRI"));	(Long)feature.getAttribute("VPH_LAVAD"));	(Long)feature.getAttribute("VPH_HMICRO"));	(Long)feature.getAttribute("VPH_AUTOM"));	(Long)feature.getAttribute("VPH_MOTO"));	(Long)feature.getAttribute("VPH_BICI"));	(Long)feature.getAttribute("VPH_RADIO"));	(Long)feature.getAttribute("VPH_TV"));	(Long)feature.getAttribute("VPH_PC"));	(Long)feature.getAttribute("VPH_TELEF"));	(Long)feature.getAttribute("VPH_CEL"));	(Long)feature.getAttribute("VPH_INTER"));	(Long)feature.getAttribute("VPH_STVP"));	(Long)feature.getAttribute("VPH_SPMVPI"));	(Long)feature.getAttribute("VPH_CVJ"));	(Long)feature.getAttribute("VPH_SINRTV"));	(Long)feature.getAttribute("VPH_SINLTC"));	(Long)feature.getAttribute("VPH_SINCIN"));	(Long)feature.getAttribute("VPH_SINTIC"));	
                shape.setArea((Double)feature.getAttribute("area"));	
                shape.setDens_pob((Double)feature.getAttribute("dens_pob"));	
                shape.setArea_ha((Double)feature.getAttribute("area_ha"));	
                shape.setDens_ha((Double)feature.getAttribute("dens_ha"));
                
                poligonoIterator.add(shape);
                }
            featureIterator.close();
            
            }
		return poligonoIterator;
		}
	
	public static List<Poligono> readGeometryIntoPolygon(List<String> shapesNames) throws IOException{
		List<Poligono> poligonoIterator = new ArrayList<>();
        
		for (String shapeName : shapesNames) {
			URL shapeURL = new File(shapeName).toURI().toURL();
        	Map<String, Serializable> params = new HashMap<>();
            params.put("url", shapeURL);
            
            DataStore dataStore = DataStoreFinder.getDataStore(params);
            String typeName = dataStore.getTypeNames()[0];
        	FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore.getFeatureSource(typeName);
        	
            FeatureCollection<SimpleFeatureType, SimpleFeature> featureCollection = source.getFeatures();
            FeatureIterator<SimpleFeature> featureIterator = featureCollection.features();
            
            while (featureIterator.hasNext()) {
            	SimpleFeature feature = featureIterator.next();
                Poligono poligono = new Poligono();
                poligono.setThe_geom((Geometry) feature.getDefaultGeometry());
                
                poligonoIterator.add(poligono);
            }
            featureIterator.close();
		}
        
       return poligonoIterator;
       }
	
	
	}