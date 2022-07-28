package mx.gob.sedatu.dgdusv.sniiv.models.services;

import org.springframework.data.jpa.repository.Query;

public interface ICuboInsusService {

    Long getAccionesByPolygon(String polygon,Integer year);

    Long getMontosByPolygon(String polygon,Integer year);

    Long getAccionesByMenPolygon(String polygon,Integer year);

    Long getAccionesByWomenPolygon(String polygon,Integer year);

    Long getMontosByMenPolygon(String polygon,Integer year);

    Long getMontosByWomenPolygon(String polygon,Integer year);
}
