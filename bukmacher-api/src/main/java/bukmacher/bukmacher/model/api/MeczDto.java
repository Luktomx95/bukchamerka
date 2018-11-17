package bukmacher.bukmacher.model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeczDto implements Serializable {

    @JsonProperty("fixture_id")
    private String idMeczu;

    @JsonProperty("event_date")
    private String dataWydarzenia;

    @JsonProperty("round")
    private String informacjeOKolejce;

    @JsonProperty( "league_id")
    private int idLigi;

    @JsonProperty("homeTeam")
    private String gospodarz;

    @JsonProperty("awayTeam")
    private String gosc;

    @JsonProperty("status")
    private String status;

    @JsonProperty("goalsHomeTeam")
    private int liczbaGoliGospodarzy;

    @JsonProperty("goalsAwayTeam")
    private int liczbaGoliGosci;
}
