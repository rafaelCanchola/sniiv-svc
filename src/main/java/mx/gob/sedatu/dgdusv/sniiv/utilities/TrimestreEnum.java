package mx.gob.sedatu.dgdusv.sniiv.utilities;

import org.checkerframework.checker.nullness.Opt;

import java.util.Optional;

public enum TrimestreEnum {
    FIRST("1er",1),
    SECOND("2do",2),
    THIRD("3er",3),
    FOURTH("4to",4);

    private final String label;
    private final Integer trimestre;

    public String getLabel(){return label;}
    public Integer getTrimestre(){return trimestre;}

    private TrimestreEnum(String label,Integer trimestre) {
        this.label = label;
        this.trimestre = trimestre;
    }

    public static Optional<Integer> getTrimestreFromLabel(String enumLabel){
        for(TrimestreEnum trim : values()){
            if(trim.getLabel().equals(enumLabel)){
                return Optional.of(trim.getTrimestre());
            }
        }
        return Optional.empty();
    }
}
