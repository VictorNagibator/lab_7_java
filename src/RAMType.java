public enum RAMType {
    DDR ("DDR"),
    DDR2 ("DDR2"),
    DDR3 ("DDR3"),
    DDR4 ("DDR4"),
    DDR5 ("DDR5");

    private final String name;

    RAMType(String name) {
        this.name = name;
    }

    @Override public String toString() {
        return name;
    }

    public static RAMType intToRAMType (int intType) {
        return switch (intType) {
            case 0 -> DDR;
            case 1 -> DDR2;
            case 2 -> DDR3;
            case 3 -> DDR4;
            case 4 -> DDR5;
            default -> throw new IllegalArgumentException("Некорректный формат данных!");
        };
    }
}