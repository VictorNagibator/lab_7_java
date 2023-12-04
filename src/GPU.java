import java.util.Scanner;

public class GPU implements OrderComponent, Cloneable {
    private String name = "";
    private double frequency;
    private int vram;

    public GPU() { }
    public GPU(String name) {
        this.name = name;
    }
    public GPU(String name, double frequency, int vram) {
        tryToSetArguments(name, frequency, vram);
    }

    public String getObjectName() { return "GPU"; }
    public String getName() { return name; }
    public double getFrequency() { return frequency; }
    public int getVRAM() { return vram; }


    public void input() {
        String name;
        double frequency;
        int vram;

        Scanner scan = new Scanner(System.in);

        System.out.print("Введите название видеокарты: ");
        name = scan.nextLine();
        System.out.print("Введите тактовую частоту графического процессора (в МГц): ");
        frequency = scan.nextDouble();
        System.out.print("Введите объем видеопамяти (в ГБ): ");
        vram = scan.nextInt();

        tryToSetArguments(name, frequency, vram);
    }

    private boolean checkArguments(String name, double frequency, int vram) {
        return (frequency >= 0) && (vram >= 0);
    }
    private void tryToSetArguments(String name, double frequency, int vram) {
        if (checkArguments(name, frequency, vram)) {
            this.name = name;
            this.frequency = frequency;
            this.vram = vram;
        }
        else throw new IllegalArgumentException("Некорректный формат данных!");
    }

    @Override public String toString() {
        String result = String.format("%s, %.1f МГц, %d ГБ", this.getName(), this.getFrequency(), this.getVRAM());
        return result;
    }
    @Override public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}