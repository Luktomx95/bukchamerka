package bukmacher.bukmacher.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class WydarzenieDto implements Serializable {

    private String idWydarzenia;

    private String nazwaWydarzenia;

    private String dataWydarzenia;

    private float kursGospodarze;

    private float kursRemis;

    private float kursGoscie;


}
