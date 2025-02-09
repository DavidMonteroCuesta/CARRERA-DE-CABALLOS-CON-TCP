package es.etg.psp.dmc.cblls.util;

public enum Responses {
    WIN("WIN"),
    LOSE("LOSE");

    private final String value;

    Responses(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
