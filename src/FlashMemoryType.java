public enum FlashMemoryType {
    SLC("SLC"),
    MLC("MLC"),
    NOR("NOR"),
    NAND("NAND"),
    NAND3D("NAND3D");

    private final String name;

    FlashMemoryType(String name) {
        this.name = name;
    }

    @Override public String toString() {
        return name;
    }

    public static FlashMemoryType intToFlashMemoryType (int intType) {
        return switch (intType) {
            case 0 -> SLC;
            case 1 -> MLC;
            case 2 -> NOR;
            case 3 -> NAND;
            case 4 -> NAND3D;
            default -> throw new IllegalArgumentException("Некорректный формат данных!");
        };
    }
}