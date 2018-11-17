package bukmacher.bukmacher.util;

import bukmacher.bukmacher.model.api.DefinicjaKolejki;
import bukmacher.bukmacher.model.enumy.Liga;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedList;
import java.util.List;

public class KolejkaUtil {


    private List<DefinicjaKolejki> zwrocDefinicjeKolejek(int idPierwszegoMeczu, String jsonZApi, Liga liga) throws Exception {
        Integer pierwszyMeczKolejki = idPierwszegoMeczu;
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonZApi);
        JsonNode mecze = node.get("api").get("fixtures");
        JsonNode meczPierwszejKolejki = mecze.get(pierwszyMeczKolejki.toString());


        List<DefinicjaKolejki> definicjaKolejki = new LinkedList<>();
        for (int i = 1; i < 39; i++) {
            DefinicjaKolejki kolejka = new DefinicjaKolejki();
            JsonNode pierwszyMeczDanejKolejki = mecze.get(pierwszyMeczKolejki.toString());
            pierwszyMeczKolejki += 9;
            JsonNode ostatniMeczKolejki = mecze.get(pierwszyMeczKolejki.toString());
            pierwszyMeczKolejki++;
            kolejka.setDataPoczatku(pierwszyMeczDanejKolejki.get("event_date").asText());
            kolejka.setDataKonca(ostatniMeczKolejki.get("event_date").asText());
            kolejka.setIdPoczatkuKolejki(pierwszyMeczDanejKolejki.get("fixture_id").asInt());
            kolejka.setIdKoncaKolejki(ostatniMeczKolejki.get("fixture_id").asInt());
            kolejka.setNumerKolejki(i);
            kolejka.setLiga(liga);
            if (!pierwszyMeczDanejKolejki.get("round").asText().equals(ostatniMeczKolejki.get("round").asText())) {
                System.out.println("BLAD!");
            }
            definicjaKolejki.add(kolejka);
        }
        return definicjaKolejki;
    }

}
