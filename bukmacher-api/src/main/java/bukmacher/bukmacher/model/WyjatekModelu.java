package bukmacher.bukmacher.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WyjatekModelu extends RuntimeException {

    private TypBledu typBledu;

    public static enum TypBledu {

        NIE_ZNALEIONO_OBIEKTU;

    }

}
