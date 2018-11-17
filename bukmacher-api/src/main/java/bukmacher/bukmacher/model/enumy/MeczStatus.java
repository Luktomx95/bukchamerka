package bukmacher.bukmacher.model.enumy;

import lombok.Getter;

@Getter
public enum MeczStatus {

    NIEROZPOCZETY("Not Started"),
    ZAKONCZONY("Match Finished");

    private final String status;

    MeczStatus(String status){
        this.status=status;
    }

}
