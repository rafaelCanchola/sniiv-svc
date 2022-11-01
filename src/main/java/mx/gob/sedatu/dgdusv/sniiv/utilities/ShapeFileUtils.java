package mx.gob.sedatu.dgdusv.sniiv.utilities;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

import mx.gob.sedatu.dgdusv.sniiv.models.entity.FeatureMexico;
import mx.gob.sedatu.dgdusv.sniiv.models.entity.Poligono;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.locationtech.jts.geom.Geometry;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;


import mx.gob.sedatu.dgdusv.sniiv.models.entity.FeatureInsus;
import org.springframework.stereotype.Component;

@Component
public class ShapeFileUtils {

    public List<FeatureInsus> readShapeIntoObjectPredio(List<String> shapesNames) throws IOException{
        List<FeatureInsus> poligonoIterator = new ArrayList<>();
        List<String> attrNames = new ArrayList<>(Arrays.asList(Constants.THE_GEOM,Constants.INSUS_CVE_ENT,Constants.INSUS_CVE_LOC,Constants.INSUS_CVE_MUN,Constants.INSUS_NOM_ENT,Constants.INSUS_NOM_MUN,Constants.INSUS_NOM_LOC,Constants.INSUS_NOM_POL,Constants.INSUS_YEAR));
        for (String shapeName : shapesNames) {
            URL shapeURL = new File(shapeName).toURI().toURL();
            Map<String, Serializable> params = new HashMap<>();
            params.put(Constants.URL, shapeURL);

            DataStore dataStore = DataStoreFinder.getDataStore(params);
            String typeName = dataStore.getTypeNames()[0];
            FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore.getFeatureSource(typeName);

            FeatureCollection<SimpleFeatureType, SimpleFeature> featureCollection = source.getFeatures();
            FeatureIterator<SimpleFeature> featureIterator = featureCollection.features();
            try{
                while (featureIterator.hasNext()) {
                SimpleFeature feature = featureIterator.next();
                if(!attrNames.stream().allMatch(a -> feature.getProperties().stream().anyMatch(f -> a.equals(f.getName().toString())))){
                    if(feature.getProperties().stream().anyMatch(f-> f.getName().toString().equals(Constants.INSUS_ACCIONES))){
                        throw new IOException("Attribute name not valid");
                    }
                }
                    FeatureInsus shape = new FeatureInsus();
                    shape.setClave_estado(new String(((String) feature.getAttribute(attrNames.get(1))).getBytes(), StandardCharsets.UTF_8));
                    shape.setClave_localidad(new String(((String) feature.getAttribute(attrNames.get(2))).getBytes(), StandardCharsets.UTF_8));
                    shape.setClave_municipio(new String(((String) feature.getAttribute(attrNames.get(3))).getBytes(), StandardCharsets.UTF_8));
                    shape.setEntidad(new String(((String) feature.getAttribute(attrNames.get(4))).getBytes(), StandardCharsets.UTF_8));
                    shape.setMunicipio(new String(((String) feature.getAttribute(attrNames.get(5))).getBytes(), StandardCharsets.UTF_8));
                    shape.setLocalidad(new String(((String) feature.getAttribute(attrNames.get(6))).getBytes(), StandardCharsets.UTF_8));
                    shape.setPoligono(new String(((String) feature.getAttribute(attrNames.get(7))).getBytes(), StandardCharsets.UTF_8));
                    try {
                        shape.setAnio(Integer.parseInt(new String(((String) feature.getAttribute(attrNames.get(8))).getBytes(), StandardCharsets.UTF_8)));
                    }catch (ClassCastException e){
                        shape.setAnio((Integer) feature.getAttribute(attrNames.get(8)));
                    }
                    poligonoIterator.add(shape);
            }
            featureIterator.close();
            }catch (NullPointerException e){
                featureIterator.close();
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        return poligonoIterator;
    }

    public List<FeatureMexico> readShapeIntoObjectEdo(List<String> shapesNames) throws IOException{
        List<FeatureMexico> poligonoIterator = new ArrayList<>();

        for (String shapeName : shapesNames) {
            URL shapeURL = new File(shapeName).toURI().toURL();
            Map<String, Serializable> params = new HashMap<>();
            params.put(Constants.URL, shapeURL);

            DataStore dataStore = DataStoreFinder.getDataStore(params);
            String typeName = dataStore.getTypeNames()[0];
            FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore.getFeatureSource(typeName);

            FeatureCollection<SimpleFeatureType, SimpleFeature> featureCollection = source.getFeatures();
            FeatureIterator<SimpleFeature> featureIterator = featureCollection.features();

            while (featureIterator.hasNext()) {
                SimpleFeature feature = featureIterator.next();
                FeatureMexico shape = new FeatureMexico();
                shape.setClave_geo(new String(((String)feature.getAttribute(Constants.MEXICO_CVEGEO)).getBytes(), StandardCharsets.UTF_8));
                shape.setNombre(new String(((String)feature.getAttribute(Constants.MEXICO_NOMGEO)).getBytes(), StandardCharsets.UTF_8));
                poligonoIterator.add(shape);
            }
            featureIterator.close();
        }
        return poligonoIterator;
    }
	public List<Poligono> readGeometryIntoPolygon(List<String> shapesNames) throws IOException{
		List<Poligono> poligonoIterator = new ArrayList<>();
        
		for (String shapeName : shapesNames) {
			URL shapeURL = new File(shapeName).toURI().toURL();
        	Map<String, Serializable> params = new HashMap<>();
            params.put(Constants.URL, shapeURL);
            
            DataStore dataStore = DataStoreFinder.getDataStore(params);
            String typeName = dataStore.getTypeNames()[0];
        	FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore.getFeatureSource(typeName);
        	
            FeatureCollection<SimpleFeatureType, SimpleFeature> featureCollection = source.getFeatures();
            FeatureIterator<SimpleFeature> featureIterator = featureCollection.features();
            
            while (featureIterator.hasNext()) {
            	SimpleFeature feature = featureIterator.next();
                if(!feature.getFeatureType().getCoordinateReferenceSystem().toWKT().contains(Constants.SHAPE_CRS)){
                    featureIterator.close();
                    throw new IOException("CRS not valid");
                }
                Poligono poligono = new Poligono();
                poligono.setThe_geom((Geometry) feature.getDefaultGeometry());
                
                poligonoIterator.add(poligono);
            }
            featureIterator.close();
		}
       return poligonoIterator;
       }
	}