public enum StatusType {
    ONHOLD ("В ожидании"),
    INPROCCESS ("В ремонте"),
    FINISHED ("Готов");

    private final String name;

    StatusType(String name) {
        this.name = name;
    }

    @Override public String toString() {
        return name;
    }

    public static StatusType intToStatusType (int intType) {
        return switch (intType) {
            case 0 -> ONHOLD;
            case 1 -> INPROCCESS;
            case 2 -> FINISHED;
            default -> throw new IllegalArgumentException("Некорректный формат данных!");
        };
    }
}