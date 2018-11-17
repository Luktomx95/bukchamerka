package bukmacher.bukmacher.serwis.mapper;

import bukmacher.bukmacher.model.dto.TypDto;
import bukmacher.bukmacher.model.encja.Typ;

public interface TypMapper {
    Typ mapDoDbo(TypDto typDto);
}
