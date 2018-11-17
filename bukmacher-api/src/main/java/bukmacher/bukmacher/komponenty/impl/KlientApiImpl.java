package bukmacher.bukmacher.komponenty.impl;

import bukmacher.bukmacher.komponenty.KlientApi;
import bukmacher.bukmacher.model.api.KursyDto;
import bukmacher.bukmacher.model.api.MeczDto;
import bukmacher.bukmacher.model.enumy.Liga;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

@Log
@Component
@AllArgsConstructor(onConstructor = @_(@Autowired))
public class KlientApiImpl implements KlientApi {

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final static String URL_POBRANIE_MECZY_LIGI = "https://api-football-v1.p.mashape.com/fixtures/league/{leagueId}";

    private final static String URL_POBRANIE_KURSOW_MECZU = "https://api-football-v1.p.mashape.com/odds/{fixtureId}";

    private final static String X_MASHAPE_HEADER = "X-Mashape-Key";

    private final static String X_MASHAPE_KEY = "7RpUlvS8zGmshZ7nAoiUUZzXvYJ0p1sefkHjsniHfxUXFkhxzu";


    @Override
    public List<MeczDto> pobierzDaneOMeczach(Liga liga) {
        URI uri = new UriTemplate(URL_POBRANIE_MECZY_LIGI).expand(liga.getIdLigi());
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(X_MASHAPE_HEADER, X_MASHAPE_KEY);
        RequestEntity<Void> request = new RequestEntity<>(headers, HttpMethod.GET, uri);

        return przemapujOdpowiedzPobranieMeczow(restTemplate.exchange(request, String.class).getBody());
    }


    @Override
    public KursyDto pobierzDaneOKursachNaMecz(String idMeczu) {
        URI uri = new UriTemplate(URL_POBRANIE_KURSOW_MECZU).expand(idMeczu);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(X_MASHAPE_HEADER, X_MASHAPE_KEY);
        RequestEntity<Void> request = new RequestEntity<>(headers, HttpMethod.GET, uri);
        return przemapujOdpowiedzPobranieKursow(restTemplate.exchange(request, String.class).getBody());
    }

    private KursyDto przemapujOdpowiedzPobranieKursow(String request) {
        KursyDto kursyDto = new KursyDto();
        try {
            JsonNode node = objectMapper.readTree(request);
            JsonNode kursy = node.get("api").get("odds").get("Win the match");
            kursyDto.setKursGospodarze(new Float(kursy.get("1").get("odd").asText()));
            kursyDto.setKursGoscie(new Float(kursy.get("2").get("odd").asText()));
            kursyDto.setKursRemis(new Float(kursy.get("N").get("odd").asText()));
            return kursyDto;
        } catch (Exception e) {
            log.info("Pobranie kursow - Blad podczas parsowania JSON-a");
        }
        return null;
    }

    private List<MeczDto> przemapujOdpowiedzPobranieMeczow(String request) {
        try {
            JsonNode node = objectMapper.readTree(request);
            JsonNode mecze = node.get("api").get("fixtures");
            List<MeczDto> listaMeczy = new LinkedList<>();
            for (JsonNode mecz : mecze) {
                try {
                    listaMeczy.add(objectMapper.readValue(mecz.toString(), MeczDto.class));
                } catch (Exception e) {
                    log.info("Parsowanie meczu - Blad podczas parsowania JSON-a");
                }
            }
            return listaMeczy;
        } catch (Exception e) {
            log.info("Parsowanie meczy - Blad podczas parsowania JSON-a");
        }
        return null;
    }
}
