import java.util.Scanner;

public class Laptop implements OrderComponent, Cloneable {
    private String name = "";
    private CPU cpu;
    private GPU gpu;
    private RAM ram;
    private Motherboard motherboard;
    private Display display;
    private DataStorage dataStorage;

    public Laptop() {
        cpu = new CPU();
        gpu = new GPU();
        ram = new RAM();
        motherboard = new Motherboard();
        display = new Display();
        dataStorage = DataStorage.DataStorageFactory.createDataStorage("HDD"); //по умолчанию в ноутбуке используется HDD
    }
    public Laptop(String name) {
        this();
        this.name = name;
    }
    public Laptop(String name, CPU cpu, GPU gpu, RAM ram, Motherboard motherboard, Display display, DataStorage dataStorage) {
        tryToSetArguments(name, cpu, gpu, ram, motherboard, display, dataStorage);
    }

    public String getObjectName() { return "Ноутбук"; }
    public String getName() { return name; }
    public CPU getCPU() { return cpu; }
    public GPU getGPU() { return gpu; }
    public RAM getRAM() { return ram; }
    public Motherboard getMotherboard() { return motherboard; }
    public Display getDisplay() { return display; }
    public DataStorage getDataStorage() { return dataStorage; }

    public void setName(String name) {
        this.name = name;
    }
    public void setCPU(CPU cpu) {
        if (checkArguments(this.name, cpu, this.gpu, this.ram, this.motherboard, this.display, this.dataStorage)) {
            this.cpu = cpu;
        }
        else throw new IllegalArgumentException("Неподходящий сокет!");
    }
    public void setGPU(GPU gpu) {
        this.gpu = gpu;
    }
    public void setRam(RAM ram) {
        if (checkArguments(this.name, this.cpu, this.gpu, ram, this.motherboard, this.display, this.dataStorage)) {
            this.ram = ram;
        }
        else throw new IllegalArgumentException("Неподходящий тип памяти!");
    }
    public void setMotherboard(Motherboard motherboard) {
        this.motherboard = motherboard;

        //если сокеты не совпадают, то старый процессор убираем
        if (!this.cpu.getSocket().equals(motherboard.getSocket())) { this.cpu = new CPU(); }
        //если не совпадают типы оперативной памяти, то старую память убираем, а новую ставим с таким же типом
        if (this.ram.getRAMType() != motherboard.getSupportedRAMType()) { this.ram = new RAM(motherboard.getSupportedRAMType()); }
    }
    public void setDisplay(Display display) {
        this.display = display;
    }
    public void setDataStorage(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public void input() {
        String name;
        CPU cpu = new CPU();
        GPU gpu = new GPU();
        RAM ram = new RAM();
        Motherboard motherboard = new Motherboard();
        Display display = new Display();
        DataStorage dataStorage;

        Scanner scan = new Scanner(System.in);

        System.out.print("Введите название ноутбука: ");
        name = scan.nextLine();
        System.out.println("\tВвод параметров процессора");
        cpu.input();
        System.out.println("\tВвод параметров видеокарты");
        gpu.input();
        System.out.println("\tВвод параметров RAM");
        ram.input();
        System.out.println("\tВвод параметров материнской платы");
        motherboard.input();
        System.out.println("\tВвод параметров экрана");
        display.input();
        int choice;
        System.out.println("\tВвод параметров хранилища");
        System.out.print("Введите тип хранилища (0 - HDD, 1 - SSD): ");
        choice = scan.nextInt();
        dataStorage = DataStorage.DataStorageFactory.createDataStorage(choice);
        dataStorage.input();
        System.out.println();

        tryToSetArguments(name, cpu, gpu, ram, motherboard, display, dataStorage);
    }

    private boolean checkArguments(String name, CPU cpu, GPU gpu, RAM ram, Motherboard motherboard, Display display, DataStorage dataStorage) {
        return (cpu.getSocket().equals(motherboard.getSocket())) && (ram.getRAMType() == motherboard.getSupportedRAMType());
    }
    private void tryToSetArguments(String name, CPU cpu, GPU gpu, RAM ram, Motherboard motherboard, Display display, DataStorage dataStorage) {
        if (checkArguments(name, cpu, gpu, ram, motherboard, display, dataStorage)) {
            this.name = name;
            this.cpu = cpu;
            this.gpu = gpu;
            this.ram = ram;
            this.motherboard = motherboard;
            this.display = display;
            this.dataStorage = dataStorage;
        }
        else throw new IllegalArgumentException("Несовместимые комплектующие!");
    }

    @Override public String toString() {
        String result = "Название модели: " + this.name + "\n" +
                this.cpu.getObjectName() + ": " + this.cpu + "\n" +
                this.gpu.getObjectName() + ": " + this.gpu + "\n" +
                this.ram.getObjectName() + ": " + this.ram + "\n" +
                this.motherboard.getObjectName() + ": " + this.motherboard + "\n" +
                this.display.getObjectName() + ": " + this.display + "\n" +
                this.dataStorage.getObjectName() + ": " + this.dataStorage;
        return result;
    }

    @Override public Object clone() throws CloneNotSupportedException {
        Laptop clone = (Laptop) super.clone();
        clone.cpu = (CPU) this.cpu.clone();
        clone.gpu = (GPU) this.gpu.clone();
        clone.ram = (RAM) this.ram.clone();
        clone.motherboard = (Motherboard) this.motherboard.clone();
        clone.display = (Display) this.display.clone();
        clone.dataStorage = (DataStorage) this.dataStorage.clone();
        return clone;
    }

    public void boostCPU() {
        if (this.cpu.getFrequency() + this.cpu.tryFreq <= this.cpu.maxFreq) {
            this.cpu = new CPU(this.cpu.getName(), this.cpu.getSocket(), this.cpu.getFrequency() + this.cpu.tryFreq, this.cpu.getNumOfCores());
        }
        else if (this.cpu.getFrequency() < this.cpu.maxFreq) {
            this.cpu = new CPU(this.cpu.getName(), this.cpu.getSocket(), this.cpu.maxFreq, this.cpu.getNumOfCores());
        }
        else System.out.println("Разгон CPU больше невозможен!");
    }
    public void boostRAM() {
        float maxFreq = this.ram.DDRFreqMax[this.ram.getRAMType().ordinal()];
        if (this.ram.getFrequency() + this.ram.tryFreq <= maxFreq) {
            ram = new RAM(this.ram.getName(), this.ram.getRAMType(), this.ram.getFrequency() + this.ram.tryFreq, this.ram.getCapacity());
        }
        else if (this.ram.getFrequency() < maxFreq) {
            ram = new RAM(this.ram.getName(), this.ram.getRAMType(), maxFreq, this.ram.getCapacity());
        }
        else System.out.println("Разгон RAM больше невозможен!");
    }
}
