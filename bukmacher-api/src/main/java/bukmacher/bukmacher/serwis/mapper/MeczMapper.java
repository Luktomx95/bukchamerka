package bukmacher.bukmacher.serwis.mapper;

import bukmacher.bukmacher.model.api.MeczDto;
import bukmacher.bukmacher.model.encja.Mecz;

import java.util.List;

public interface MeczMapper {
    Mecz mapToDbo(MeczDto meczDto);
    MeczDto mapToDto(Mecz mecz);
    List<Mecz> mapListToDbo(List<MeczDto> meczeDto);
}
