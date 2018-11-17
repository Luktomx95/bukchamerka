package bukmacher.bukmacher.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TypDto implements Serializable {
    private long idMeczu;
    private String typMecz;
    private float kurs;
    private float stawka;
    private long idUzytkownika;
}
