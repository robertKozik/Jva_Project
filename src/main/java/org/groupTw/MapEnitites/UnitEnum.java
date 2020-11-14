package org.groupTw.MapEnitites;

public enum UnitEnum {
    ARCHER("archer"),
    CATAPULT("catapult"),
    MERCENARY("mercenary"),
    SIEGE("siege"),
    TOWER("tower"),
    WARRIOR("warrior"),
    RED("red"),
    BLUE("blue");

    private String value;

    UnitEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
