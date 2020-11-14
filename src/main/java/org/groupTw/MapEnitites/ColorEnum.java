package org.groupTw.MapEnitites;

public enum ColorEnum {
    RED("red"),
    BLUE("blue");

    private String value;

    ColorEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
