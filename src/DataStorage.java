import java.util.Scanner;

public abstract class DataStorage implements OrderComponent, Cloneable {
    private int capacity = 0;
    private DataTransferInterface transferInterface = DataTransferInterface.SATA;
    private float formFactor = 0;
    private String brand = "";

    protected DataStorage() { }
    protected DataStorage(DataTransferInterface transferInterface) { this.transferInterface = transferInterface; }
    protected DataStorage(int capacity, DataTransferInterface transferInterface, String brand, float formFactor) {
        tryToSetArguments(capacity, transferInterface, brand, formFactor);
    }

    //вспомогательный класс "фабрика" объектов DataStorage
    static class DataStorageFactory {
        public static DataStorage createDataStorage(String name) {
            return switch (name) {
                case "HDD" -> new HDD();
                case "SSD" -> new SSD();
                default -> throw new IllegalArgumentException("Некорректный формат данных!");
            };
        }
        public static DataStorage createDataStorage(int choice) {
            return switch (choice) {
                case 0 -> new HDD();
                case 1 -> new SSD();
                default -> throw new IllegalArgumentException("Некорректный формат данных!");
            };
        }
    }

    public int getCapacity() { return capacity; }
    public DataTransferInterface getInterface() { return transferInterface; }
    public String getName() { return brand; }
    public float getFormFactor() { return formFactor; }

    public void input() {
        int capacity;
        DataTransferInterface type;
        String brand;
        float formFactor;

        Scanner scan = new Scanner(System.in);

        System.out.print("Введите вместимость (в ГБ): ");
        capacity = scan.nextInt();
        System.out.print("Введите интерфейс подключения (PATA - 0, SATA - 1, SAS - 2, NVMe - 3): ");
        type = DataTransferInterface.intToDataTransferInterface(scan.nextInt());
        scan.nextLine(); //очистка потока
        System.out.print("Введите производителя: ");
        brand = scan.nextLine();
        System.out.print("Введите форм фактор (в дюймах): ");
        formFactor = scan.nextFloat();

        tryToSetArguments(capacity, type, brand, formFactor);
    }

    private boolean checkArguments(int capacity, DataTransferInterface transferInterface, String brand, float formFactor) {
        return (capacity >= 0) && (formFactor >= 0);
    }

    private void tryToSetArguments(int capacity, DataTransferInterface transferInterface, String brand, float formFactor) {
        if (checkArguments(capacity, transferInterface, brand, formFactor)) {
            this.capacity = capacity;
            this.transferInterface = transferInterface;
            this.brand = brand;
            this.formFactor = formFactor;
        }
        else throw new IllegalArgumentException("Некорректный формат данных!");
    }

    @Override public String toString()  {
        String result = String.format("%s, %d ГБ, %s, %.1f", this.getName(), this.getCapacity(), this.getInterface(), this.getFormFactor());
        return result;
    }
    @Override public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}