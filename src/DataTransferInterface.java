public enum DataTransferInterface {
    PATA("PATA"),
    SATA("SATA"),
    SAS("SAS"),
    NVME("NVME");

    private final String name;

    DataTransferInterface(String name) {
        this.name = name;
    }

    @Override public String toString() {
        return name;
    }

    public static DataTransferInterface intToDataTransferInterface (int intType) {
        return switch (intType) {
            case 0 -> PATA;
            case 1 -> SATA;
            case 2 -> SAS;
            case 3 -> NVME;
            default -> throw new IllegalArgumentException("Некорректный формат данных!");
        };
    }
}