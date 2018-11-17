package bukmacher.bukmacher.serwis;

import javax.transaction.Transactional;

public interface AsynchronicznaObslugaMeczy {
    @Transactional
    void aktualizujWynikiMeczy();

    @Transactional
    void rozliczTypy();
}
