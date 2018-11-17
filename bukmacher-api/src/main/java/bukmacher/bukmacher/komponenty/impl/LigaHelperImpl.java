package bukmacher.bukmacher.komponenty.impl;

import bukmacher.bukmacher.komponenty.LigaHelper;
import bukmacher.bukmacher.model.enumy.Liga;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Component
public class LigaHelperImpl implements LigaHelper {

    public static List<Liga> ligi = new LinkedList<>();

    @PostConstruct
    private void inicjalizacjaLig(){
        ligi.add( Liga.LALIGA);
        ligi.add( Liga.PREMIER_LEAUGE);
    }

    @Override
    public Liga znajdzLigePoId(int idLigi) {
        switch (idLigi) {
            case 87:
                return Liga.LALIGA;
            case 2:
                return Liga.PREMIER_LEAUGE;

            case 94:
                return Liga.SERIEA;

            case 8:
                return Liga.BUNDESLIGA;

            case 4:
                return Liga.LIGUE1;

            case 16:
                return Liga.EXTRAKLASA;
        }
        return null;
    }
}

