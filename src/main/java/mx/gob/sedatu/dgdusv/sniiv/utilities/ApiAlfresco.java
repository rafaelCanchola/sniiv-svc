package mx.gob.sedatu.dgdusv.sniiv.utilities;

import org.springframework.stereotype.Component;

@Component
public class ApiAlfresco {

    public String sharedUrlAlfresco(String nodeId,String nameFile){
        return Constants.AlfrescoEndPoints.ALFRESCO_SITE+nodeId+ Constants.AlfrescoEndPoints.CONTENT+nameFile+ Constants.AlfrescoEndPoints.DOWNLOAD_SHARED;
    }

    public String generateUrl(){
        return Constants.AlfrescoEndPoints.ROOT_SITE+Constants.AlfrescoEndPoints.SHARE;
    }

    public String folderNode(String idFolder){
        return Constants.AlfrescoEndPoints.ROOT_SITE+Constants.AlfrescoEndPoints.NODES+idFolder+Constants.AlfrescoEndPoints.CHILDREN;
    }

    public String createTicket(){
        return Constants.AlfrescoEndPoints.ROOT_SITE+Constants.AlfrescoEndPoints.TICKETS;
    }


}
