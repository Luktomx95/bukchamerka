package bukmacher.bukmacher.controller.impl;

import bukmacher.bukmacher.controller.BukmacherRestController;
import bukmacher.bukmacher.model.dto.AktualnaKolejkaDto;
import bukmacher.bukmacher.model.dto.TypDto;
import bukmacher.bukmacher.model.dto.WydarzenieDto;
import bukmacher.bukmacher.serwis.BukmacherService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("/api")
public class BukmacherRestControllerImpl implements BukmacherRestController {

    private BukmacherService bukmacherService;

    @Override
    public ResponseEntity<AktualnaKolejkaDto> pobierzAktualnyNumerKolejkiWLidze(long idLigi) {
        return ResponseEntity.ok(bukmacherService.zwrocAktualnyNumerKolejkiWLidze(idLigi));
    }

    @Override
    public ResponseEntity<List<WydarzenieDto>> pobierzWydarzeniaWDanejKolejceWLidze(@PathVariable("idLigi") long idLigi, @PathVariable("kolejka") int numerKolejki) {
        return ResponseEntity.ok(bukmacherService.przygotujListeWydarzen(idLigi,numerKolejki));
    }

    @Override
    public ResponseEntity<Void> zapiszTypy(@RequestBody TypDto typDto) {
        bukmacherService.zapiszTyp(typDto);
        return ResponseEntity.ok(null);
    }
}
