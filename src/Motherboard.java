import java.util.Scanner;

public class Motherboard implements OrderComponent, Cloneable {
    private String name = "";
    private String socket = "";
    private String chipset = "";
    private RAMType supportedRAMType = RAMType.DDR;

    public Motherboard() { }
    public Motherboard(String name) {
        this.name = name;
    }
    public Motherboard(String name, String socket, String chipset, RAMType supportedRAMType) {
        this(name);
        this.socket = socket;
        this.chipset = chipset;
        this.supportedRAMType = supportedRAMType;
    }

    public String getObjectName() { return "Материнская плата"; }
    public String getName() { return name; }
    public String getSocket() { return socket; }
    public String getChipset() { return chipset; }
    public RAMType getSupportedRAMType() { return supportedRAMType; }


    public void input() {
        String name, socket, chipset;
        RAMType supportedRAMType;

        Scanner scan = new Scanner(System.in);

        System.out.print("Введите название материнской платы: ");
        name = scan.nextLine();
        System.out.print("Введите сокет: ");
        socket = scan.nextLine();
        System.out.print("Введите чипсет: ");
        chipset = scan.nextLine();
        System.out.print("Введите поддерживаемый тип памяти (DDR - 0, DDR2 - 1, DDR3 - 2, DDR4 - 3, DDR5 - 4): ");
        supportedRAMType = RAMType.intToRAMType(scan.nextInt());

        this.name = name;
        this.socket = socket;
        this.chipset = chipset;
        this.supportedRAMType = supportedRAMType;
    }

    @Override public String toString() {
        return (this.getName() + ", " + this.getSocket() + ", " + this.getChipset());
    }
    @Override public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}