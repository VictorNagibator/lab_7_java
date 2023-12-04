import java.util.Scanner;

public class Display implements OrderComponent, Cloneable {
    private String name = "";
    private int width;
    private int height;
    private int refreshRate;

    public Display() { }
    public Display(int width, int height) { tryToSetArguments(this.name, width, height, this.refreshRate); }
    public Display(String name, int width, int height, int refreshRate) {
        tryToSetArguments(name, width, height, refreshRate);
    }

    public String getObjectName() { return "Экран"; }
    public String getName() { return name; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getRefreshRate() { return refreshRate; }


    public void input() {
        String name;
        int width, height, refreshRate;

        Scanner scan = new Scanner(System.in);

        System.out.print("Введите название экрана: ");
        name = scan.nextLine();
        System.out.print("Введите ширину экрана (в пикселях): ");
        width = scan.nextInt();
        System.out.print("Введите высоту экрана (в пикселях): ");
        height = scan.nextInt();
        System.out.print("Введите частоту обновления экрана (в Гц): ");
        refreshRate = scan.nextInt();

        tryToSetArguments(name, width, height, refreshRate);
    }

    private boolean checkArguments(String name, int width, int height, int refreshRate) {
        return (width >= 0) && (height >= 0) && (refreshRate >= 0);
    }
    private void tryToSetArguments(String name, int width, int height, int refreshRate) {
        if (checkArguments(name, width, height, refreshRate)) {
            this.width = width;
            this.height = height;
            this.refreshRate = refreshRate;
        }
        else throw new IllegalArgumentException("Некорректный формат данных!");
    }

    @Override public String toString() {
        return (this.getName() + ", " + this.getWidth() + "x" + this.getHeight() + ", "  + this.getRefreshRate() + " Гц");
    }
    @Override public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}