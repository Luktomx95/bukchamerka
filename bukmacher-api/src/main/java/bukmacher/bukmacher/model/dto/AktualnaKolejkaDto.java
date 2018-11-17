package bukmacher.bukmacher.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AktualnaKolejkaDto implements Serializable {
    private int idLigi;
    private int aktualnyNumerKolejki;

    public AktualnaKolejkaDto idLigi(int idLigi){
        this.idLigi=idLigi;
        return this;
    }

    public AktualnaKolejkaDto aktualnyNumerKolejki(int aktualnyNumerKolejki){
        this.aktualnyNumerKolejki=aktualnyNumerKolejki;
        return this;
    }



}
