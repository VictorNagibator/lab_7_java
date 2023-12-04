import java.util.Scanner;

public class HDD extends DataStorage implements Cloneable {
    private int spindleSpeed = 0;

    protected HDD() { super(); }
    public HDD(DataTransferInterface transferInterface) { super(transferInterface); }
    public HDD(int capacity, DataTransferInterface transferInterface, String brand, float formFactor, int spindleSpeed)
    {
        super(capacity, transferInterface, brand, formFactor);
        tryToSetArguments(spindleSpeed);
    }

    public String getObjectName() { return "HDD"; }
    public int getSpindleSpeed() { return spindleSpeed; }


    public void input() {
        super.input();
        int spindleSpeed;

        Scanner scan = new Scanner(System.in);

        System.out.print("Введите cкорость вращения шпинделя: ");
        spindleSpeed = scan.nextInt();

        tryToSetArguments(spindleSpeed);
    }

    @Override public String toString() {
        String result = super.toString() + ", " + this.getSpindleSpeed();
        return result;
    }
    @Override public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    private boolean checkArguments(int spindleSpeed) {
        return spindleSpeed >= 0;
    }

    private void tryToSetArguments(int spindleSpeed) {
        if (checkArguments(spindleSpeed)) {
            this.spindleSpeed = spindleSpeed;
        }
        else throw new IllegalArgumentException("Некорректный формат данных!");
    }
}