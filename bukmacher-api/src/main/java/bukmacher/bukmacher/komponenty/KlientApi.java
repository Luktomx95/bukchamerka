package bukmacher.bukmacher.komponenty;

import bukmacher.bukmacher.model.api.KursyDto;
import bukmacher.bukmacher.model.api.MeczDto;
import bukmacher.bukmacher.model.enumy.Liga;

import java.util.List;

public interface KlientApi {

    List<MeczDto> pobierzDaneOMeczach(Liga liga);

    KursyDto pobierzDaneOKursachNaMecz(String idMeczu);

}
