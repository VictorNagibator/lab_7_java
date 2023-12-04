import java.util.Scanner;

public class CPU implements OrderComponent, Cloneable {
    private String name = "";
    private String socket = "";
    private double frequency;
    private int numOfCores;
    public final double maxFreq = 9.0; //условная максимальная тактовая частота для процессора
    public final double tryFreq = 0.2; //условное повышение частоты для разгона

    public CPU() { }
    public CPU(String name) {
        this.name = name;
    }
    public CPU(String name, String socket, double frequency, int numOfCores) {
        tryToSetArguments(name, socket, frequency, numOfCores);
    }


    public String getObjectName() { return "CPU"; };
    public String getName() { return name; }
    public String getSocket() { return socket; }
    public double getFrequency() { return frequency; }
    public int getNumOfCores() { return numOfCores; }


    public void input() {
        String name, socket;
        double frequency;
        int numOfCores;

        Scanner scan = new Scanner(System.in);

        System.out.print("Введите название процессора: ");
        name = scan.nextLine();
        System.out.print("Введите сокет: ");
        socket = scan.nextLine();
        System.out.print("Введите его тактовую частоту (в ГГц): ");
        frequency = scan.nextDouble();
        System.out.print("Введите количество ядер: ");
        numOfCores = scan.nextInt();

        tryToSetArguments(name, socket, frequency, numOfCores);
    }

    private boolean checkArguments(String name, String socket, double frequency, int numOfCores) {
        return (frequency >= 0) && (frequency < maxFreq) && (numOfCores >= 0);
    }
    private void tryToSetArguments(String name, String socket, double frequency, int numOfCores) {
        if (checkArguments(name, socket, frequency, numOfCores)) {
            this.name = name;
            this.socket = socket;
            this.frequency = frequency;
            this.numOfCores = numOfCores;
        }
        else throw new IllegalArgumentException("Некорректный формат данных!");
    }

    @Override public String toString() {
        //здесь используется format из-за погрешности при расчете частоты, чтобы "красиво" выводить дробные числа
        String result = String.format("%s, %s, %.1f ГГц, %d-ядерный", this.getName(), this.getSocket(), this.getFrequency(), this.getNumOfCores());
        return result;
    }
    @Override public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}