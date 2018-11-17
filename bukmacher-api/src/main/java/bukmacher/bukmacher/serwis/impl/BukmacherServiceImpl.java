package bukmacher.bukmacher.serwis.impl;

import bukmacher.bukmacher.komponenty.KlientApi;
import bukmacher.bukmacher.komponenty.LigaHelper;
import bukmacher.bukmacher.model.dto.AktualnaKolejkaDto;
import bukmacher.bukmacher.model.dto.TypDto;
import bukmacher.bukmacher.model.dto.WydarzenieDto;
import bukmacher.bukmacher.model.encja.Mecz;
import bukmacher.bukmacher.repository.AktualnaKolejkaRepository;
import bukmacher.bukmacher.repository.MeczRepository;
import bukmacher.bukmacher.repository.TypRepository;
import bukmacher.bukmacher.serwis.BukmacherService;
import bukmacher.bukmacher.serwis.mapper.AktualnaKolejkaMapper;
import bukmacher.bukmacher.serwis.mapper.TypMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @_(@Autowired))
public class BukmacherServiceImpl implements BukmacherService {

    private final MeczRepository meczRepo;

    private final LigaHelper ligaHelper;

    private final KlientApi klientApi;

    private final AktualnaKolejkaRepository aktualnaKolejkaRepository;

    private final AktualnaKolejkaMapper aktualnaKolejkaMapper;

    private final TypRepository typRepository;

    private final TypMapper typMapper;

    @Override
    public List<WydarzenieDto> przygotujListeWydarzen(long idLigi, int numerKolejki) {
        List<Mecz> listaMeczy = meczRepo.findAllByLigaAndKolejka(ligaHelper.znajdzLigePoId((int) idLigi), numerKolejki);
        return listaMeczy.stream().map(this::przygotujDaneOWydarzeniu).collect(Collectors.toList());
    }

    private WydarzenieDto przygotujDaneOWydarzeniu(Mecz mecz) {
        WydarzenieDto wydarzenieDto = new WydarzenieDto();
        wydarzenieDto.setIdWydarzenia(mecz.getId().toString());
        wydarzenieDto.setDataWydarzenia(mecz.getDataWydarzenia().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        wydarzenieDto.setNazwaWydarzenia(mecz.getNazwaWydarzenia());

//        KursyDto kursyNaMecz = klientApi.pobierzDaneOKursachNaMecz(mecz.getId().toString());
//        wydarzenieDto.setKursGospodarze(kursyNaMecz.getKursGospodarze());
//        wydarzenieDto.setKursRemis(kursyNaMecz.getKursRemis());
//        wydarzenieDto.setKursGoscie(kursyNaMecz.getKursGoscie());


        wydarzenieDto.setKursGoscie(1.5f);
        wydarzenieDto.setKursRemis(3.24f);
        wydarzenieDto.setKursGospodarze(5.0f);

        return wydarzenieDto;
    }

    @Override
    public Integer zwrocAktualnyNumerKolejki() {
        return null;
    }

    @Override
    public AktualnaKolejkaDto zwrocAktualnyNumerKolejkiWLidze(long idLigi) {
        return aktualnaKolejkaMapper.mapToDto(aktualnaKolejkaRepository.findFirstByIdLigi((int)idLigi));
    }

    @Override
    public void zapiszTyp(TypDto typDto) {
        typRepository.save(typMapper.mapDoDbo(typDto));
    }


}
