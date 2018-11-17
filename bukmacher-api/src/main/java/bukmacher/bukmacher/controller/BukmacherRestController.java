package bukmacher.bukmacher.controller;

import bukmacher.bukmacher.model.dto.AktualnaKolejkaDto;
import bukmacher.bukmacher.model.dto.TypDto;
import bukmacher.bukmacher.model.dto.WydarzenieDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface BukmacherRestController {

    @RequestMapping(path = "/liga/{idLigi}/pobierz-numer-kolejki", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    ResponseEntity<AktualnaKolejkaDto> pobierzAktualnyNumerKolejkiWLidze(@PathVariable("idLigi") long idLigi);

    @RequestMapping(path = "/mecze/{idLigi}/{kolejka}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    ResponseEntity<List<WydarzenieDto>> pobierzWydarzeniaWDanejKolejceWLidze(@PathVariable("idLigi") long idLigi, @PathVariable("kolejka") int numerKolejki);

    @RequestMapping(path = "/typy/zapisz-typ", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    ResponseEntity<Void> zapiszTypy(@RequestBody TypDto typDto);
}
