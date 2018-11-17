package bukmacher.bukmacher.model.api;

import bukmacher.bukmacher.model.encja.Kolejka;
import bukmacher.bukmacher.model.enumy.Liga;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class DefinicjaKolejki {

    private int idPoczatkuKolejki;
    private int idKoncaKolejki;
    private String dataPoczatku;
    private String dataKonca;
    private int numerKolejki;
    private Liga liga;

    public Kolejka mapToDbo(){
        Kolejka kolejka = new Kolejka();
        kolejka.setIdLigi(this.liga.getIdLigi());
        kolejka.setNumerKolejki(this.numerKolejki);
        kolejka.setPoczatekKolejki(LocalDate.parse(this.dataPoczatku, DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        kolejka.setKoniecKolejki(LocalDate.parse(this.dataKonca, DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        return kolejka;
    }
}
