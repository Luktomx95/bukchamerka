package bukmacher.bukmacher.model.enumy;

import lombok.Getter;

@Getter
public enum Liga {

    LALIGA(87),
    PREMIER_LEAUGE(2),
    SERIEA(94),
    BUNDESLIGA(8),
    LIGUE1(4),
    EXTRAKLASA(16);

    private final int idLigi;

    Liga(int idLigi) {
        this.idLigi = idLigi;
    }

}
