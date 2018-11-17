package bukmacher.bukmacher;

import bukmacher.bukmacher.komponenty.KlientApi;
import bukmacher.bukmacher.repository.AktualnaKolejkaRepository;
import bukmacher.bukmacher.repository.KolejkaRepository;
import bukmacher.bukmacher.repository.MeczRepository;
import bukmacher.bukmacher.repository.TypRepository;
import bukmacher.bukmacher.serwis.AsynchronicznaObslugaMeczy;
import bukmacher.bukmacher.serwis.BukmacherService;
import bukmacher.bukmacher.serwis.mapper.MeczMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BukmacherApplication.class)
@WebAppConfiguration
public class BukmacherApplicationTests {

    @Autowired
    private KlientApi klientApi;

    @Autowired
    private MeczMapper meczMapper;

    @Autowired
    private KolejkaRepository kolejkaRepo;

    @Autowired
    private MeczRepository meczRepo;

    @Autowired
    private BukmacherService bukmacherService;

    @Autowired
    private AktualnaKolejkaRepository aktualnaKolejkaRepository;

    @Autowired
    private AsynchronicznaObslugaMeczy asynchronicznaObslugaMeczy;

    @Autowired
    private TypRepository typRepository;

    @Test
    public void test() {



    }


}
