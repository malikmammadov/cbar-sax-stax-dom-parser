package domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class Cbar {
    public static final String URL = "https://www.cbar.az/currencies/"
            + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + ".xml";

    private Cbar() {
    }
}
