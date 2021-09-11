package Task.enums;

public enum UnitType {
    NOT_CHOSEN("не выбрано"),
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
