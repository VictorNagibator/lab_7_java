import java.util.Scanner;

public class SSD extends DataStorage implements Cloneable {
    private FlashMemoryType typeOfFlashMemory = FlashMemoryType.NAND3D;

    protected SSD() { super(); }
    public SSD(DataTransferInterface transferInterface) { super(transferInterface); }
    public SSD(int capacity, DataTransferInterface transferInterface, String brand, float formFactor, FlashMemoryType typeOfFlashMemory)
    {
        super(capacity, transferInterface, brand, formFactor);
        this.typeOfFlashMemory = typeOfFlashMemory;
    }

    public String getObjectName() { return "SSD"; }
    public FlashMemoryType getTypeOfFlashMemory() { return typeOfFlashMemory; }


    public void input() {
        FlashMemoryType typeOfFlashMemory;

        Scanner scan = new Scanner(System.in);

        System.out.print("Введите тип флеш-памяти (0 - SLC, 1 - MLC, 2 - NOR, 3 - NAND, 4 - 3DNAND): ");
        typeOfFlashMemory = FlashMemoryType.intToFlashMemoryType(scan.nextInt());

        this.typeOfFlashMemory = typeOfFlashMemory;
    }

    @Override public String toString() {
        String name = super.toString() + ", " + getTypeOfFlashMemory();
        return name;
    }
    @Override public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}