package enums;

public enum UnitType {
    thing("штук"),
    kilo("килограмм"),
    gram("грамм"),
    liter("литр");

    private String name;

    UnitType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
