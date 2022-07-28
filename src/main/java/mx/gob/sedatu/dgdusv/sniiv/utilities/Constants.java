package mx.gob.sedatu.dgdusv.sniiv.utilities;

public class Constants {

    public static final String TEMP_DIRECTORY= "SniivTempDirectory";

    public static final String POLIGONO_NAME= "poli";
    public static final String ESTADO_NAME= "edos";
    public static final String MUNICIPIO_NAME= "muni";

    //Feature INSUS
    public static final String INSUS_CVE_ENT = "CVE_ENT";
    public static final String INSUS_CVE_MUN =  "CVE_MUN";
    public static final String INSUS_CVE_LOC = "CVE_LOC";
    public static final String INSUS_NOM_ENT = "NOM_EDO";
    public static final String INSUS_NOM_MUN = "NOM_MUN";
    public static final String INSUS_NOM_LOC = "NOM_LOC";
    public static final String INSUS_CVE_INEGI = "CVE_INEGI";
    public static final String INSUS_NOM_POL = "NOM_POL";
    public static final String INSUS_YEAR = "AÑO";
    public static final String INSUS_ACCIONES = "ACCIONES";
    public static final String THE_GEOM = "the_geom";
    public static final String MEXICO_CVEGEO = "CVEGEO";
    public static final String MEXICO_NOMGEO = "NOMGEO";

    public static final String DIRECTORY_UPLOAD_ROOT = "app.upload.dir";
    public static final String DIRECTORY_UPLOAD_SNIIV = "app.upload.sniiv";
    public static final String DIRECTORY_UPLOAD_SNIIV_TEMP = "app.upload.sniiv.temporal";
    public static final String DIRECTORY_UPLOAD_SNIIV_PUNTOS = "app.upload.sniiv.puntos";

    public static final String SEDATU = "sedatu";
    public static final String URL = "url";
    public static final String ZIP_EXT = ".zip";
    public static final String CSV_EXT = ".csv";
    public static final String PDF_EXT = ".pdf";
    public static final String CSV_NAME = "PNV";
    public static final String SHAPE_EXT = ".SHP";
    public static final String SHAPE_NAME = "PRAH";

    public static final String SHAPE_CRS = "WGS_1984";

    public static class AlfrescoFolders{
        public static final String FOLDER_PUNTOS = "97f4e1a0-fdd6-4870-9394-1d26fae23739";
        public static final String FOLDER_ZIPS = "350f40d3-c8b0-4da1-a809-9a766de5340e";
        public static final String FOLDER_PNV_INFORMES = "36b4f619-4cc1-4da3-b855-a1dbf545d968";

    }

    public static class AlfrescoEndPoints{
        public static final String ALFRESCO_SITE = "https://sistemas.sedatu.gob.mx/repositorio/proxy/alfresco-noauth/api/internal/shared/node/";
        public static final String ROOT_SITE = "https://sistemas.sedatu.gob.mx/alfresco/api/-default-/public/";
        public static final String NODES = "alfresco/versions/1/nodes/";
        public static final String SHARE = "alfresco/versions/1/shared-links";
        public static final String TICKETS = "authentication/versions/1/tickets/";
        public static final String CHILDREN = "/children";
        public static final String CONTENT = "/content/";
        public static final String DOWNLOAD_SHARED = "?&a=true";
    }

    public static class InformePnv{
        public static final String FIRST_TRIM = "1er";
        public static final String SECOND_TRIM = "2do";
        public static final String THIRD_TRIM = "3er";
        public static final String FOURTH_TRIM = "4to";
        public static final String PNV_NAME = "informe_trimestral_PNV";
        public static final String SPLIT_CHAR = "_";

    }
}
