package bukmacher.bukmacher.model.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class KursyDto implements Serializable {

    private float kursGospodarze;

    private float kursGoscie;

    private float kursRemis;

}
