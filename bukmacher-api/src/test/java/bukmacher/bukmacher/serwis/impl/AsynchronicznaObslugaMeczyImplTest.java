package bukmacher.bukmacher.serwis.impl;

import bukmacher.bukmacher.BukmacherApplication;
import bukmacher.bukmacher.model.encja.AktualnaKolejka;
import bukmacher.bukmacher.model.encja.Mecz;
import bukmacher.bukmacher.model.encja.Typ;
import bukmacher.bukmacher.model.encja.Uzytkownik;
import bukmacher.bukmacher.model.enumy.MeczStatus;
import bukmacher.bukmacher.model.enumy.MeczWynik;
import bukmacher.bukmacher.model.enumy.TypStatus;
import bukmacher.bukmacher.repository.AktualnaKolejkaRepository;
import bukmacher.bukmacher.repository.MeczRepository;
import bukmacher.bukmacher.repository.TypRepository;
import bukmacher.bukmacher.repository.UzytkownikRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BukmacherApplication.class)
@WebAppConfiguration
public class AsynchronicznaObslugaMeczyImplTest {

    @Autowired
    private MeczRepository meczRepo;

    @Autowired
    private TypRepository typRepo;

    @Autowired
    private UzytkownikRepository uzytkownikRepo;

    @Autowired
    private AktualnaKolejkaRepository aktualnaKolejkaRepo;

    @Autowired
    private AsynchronicznaObslugaMeczyImpl asynchronicznaObslugaMeczy;

    @Before
    public void init() {



        Uzytkownik uzytkownik = new Uzytkownik();
        Optional<Uzytkownik> uzytkownikTestowy = uzytkownikRepo.findOneByNazwa("Test");
        if(uzytkownikTestowy.isPresent()) uzytkownik = uzytkownikTestowy.get();
        else {
            uzytkownik.setNazwa("Test");
        }
        uzytkownik.setStanKonta(new BigDecimal(5000));

        AktualnaKolejka aktualnaKolejka = aktualnaKolejkaRepo.findFirstByIdLigi(87);
        aktualnaKolejka.setNumerAktualnejKolejki(12);
        aktualnaKolejkaRepo.save(aktualnaKolejka);

        Mecz meczTypWygrany = meczRepo.getOne(27252L);
        Mecz meczTypPrzegrany = meczRepo.getOne(27251L);

        meczTypWygrany.setWynikMeczu(null);
        meczTypPrzegrany.setWynikMeczu(null);
        meczTypPrzegrany.setStatusMeczu(MeczStatus.NIEROZPOCZETY);
        meczTypWygrany.setStatusMeczu(MeczStatus.NIEROZPOCZETY);


        Typ typTestowy = new Typ();
        typTestowy.setTypMeczWynik(MeczWynik.GOSCIE);
        typTestowy.setStawka(400f);
        typTestowy.setUzytkownik(uzytkownik);
        typTestowy.setMecz(meczTypWygrany);
        typTestowy.setKurs(1.5f);
        typTestowy.setId(555L);
        typTestowy.setStatusTypu(TypStatus.OTWARTY);

        Typ typtestowy1 = new Typ();
        typtestowy1.setTypMeczWynik(MeczWynik.GOSPODARZ);
        typtestowy1.setStawka(400f);
        typtestowy1.setUzytkownik(uzytkownik);
        typtestowy1.setMecz(meczTypPrzegrany);
        typtestowy1.setKurs(1.5f);
        typtestowy1.setId(556L);
        typtestowy1.setStatusTypu(TypStatus.OTWARTY);

        meczRepo.save(meczTypPrzegrany);
        meczRepo.save(meczTypWygrany);

        uzytkownikRepo.save(uzytkownik);

        typRepo.save(typTestowy);
        typRepo.save(typtestowy1);


    }


    @Test
    public void aktualizujWynikiMeczyIRozliczTypyTest() {
        asynchronicznaObslugaMeczy.aktualizujWynikiMeczyIRozliczTypy();
        Uzytkownik uzytkownik = uzytkownikRepo.findOneByNazwa("Test").get();
        Assert.assertEquals(uzytkownik.getStanKonta(), new BigDecimal(5600));

        List<Typ> listaTypowDoRozliczenia = typRepo.findAllByStatusTypu(TypStatus.OTWARTY);
        Assert.assertEquals(listaTypowDoRozliczenia.size(), 0);

        Mecz meczZamkniety = meczRepo.getOne(27252L);
        Assert.assertEquals(meczZamkniety.getWynikMeczu(), MeczWynik.GOSCIE);
        Assert.assertEquals(meczZamkniety.getStatusMeczu(), MeczStatus.ZAKONCZONY);


    }

}