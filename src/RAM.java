import java.util.Scanner;

public class RAM implements OrderComponent, Cloneable {
    private String name = "";
    private RAMType type = RAMType.DDR;
    private double frequency;
    private int capacity;
    public final double tryFreq = 50; //условное повышение частоты для разгона
    public final int[] DDRFreqMax = { 400, 1066, 2400, 3333, 6400 }; //массив максимально возможных частот для каждого типа памяти

    public RAM() { }
    public RAM(String name) {
        this.name = name;
    }
    public RAM(RAMType type) {
        this.type = type;
    }
    public RAM(String name, RAMType type, double frequency, int capacity) {
        tryToSetArguments(name, type, frequency, capacity);
    }

    public String getObjectName() { return "RAM"; }
    public String getName() { return name; }
    public RAMType getRAMType() { return type; }
    public double getFrequency() { return frequency; }
    public int getCapacity() { return capacity; }


    public void input() {
        String name;
        RAMType type;
        double frequency;
        int capacity;

        Scanner scan = new Scanner(System.in);

        System.out.print("Введите название RAM: ");
        name = scan.nextLine();
        System.out.print("Введите тип памяти (DDR - 0, DDR2 - 1, DDR3 - 2, DDR4 - 3, DDR5 - 4): ");
        type = RAMType.intToRAMType(scan.nextInt());
        System.out.print("Введите тактовую частоту (в МГц): ");
        frequency = scan.nextDouble();
        System.out.print("Введите объем (в ГБ): ");
        capacity = scan.nextInt();

        tryToSetArguments(name, type, frequency, capacity);
    }

    private boolean checkArguments(String name, RAMType type, double frequency, int capacity) {
        return (frequency >= 0) && (frequency <= DDRFreqMax[type.ordinal()]) && (capacity >= 0);
    }
    private void tryToSetArguments(String name, RAMType type, double frequency, int capacity) {
        if (checkArguments(name, type, frequency, capacity)) {
            this.name = name;
            this.type = type;
            this.frequency = frequency;
            this.capacity = capacity;
        }
        else throw new IllegalArgumentException("Некорректный формат данных!");
    }

    @Override public String toString() {
        String result = String.format("%s, %s, %d ГБ, %.1f МГц", this.getName(), this.getRAMType().toString(), this.getCapacity(), this.getFrequency());
        return result;
    }
    @Override public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}