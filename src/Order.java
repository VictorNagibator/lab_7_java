import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Order<T extends OrderComponent> {
    private final int num;
    private T objectOfOrder;
    private StatusType status;
    private static int numOfOrders = 0; //количество заказов

    private Order() {
        this.num = ++numOfOrders;
        this.status = StatusType.ONHOLD;
    }
    public Order(T objectOfOrder) {
        this();
        this.objectOfOrder = objectOfOrder;
    }
    public Order(T objectOfOrder, StatusType status) {
        this(objectOfOrder);
        this.status = status;
    }

    public int getNum() { return num; }
    public static int getLastNumOfOrder() { return numOfOrders; }
    public T getObjectOfOrder() { return objectOfOrder; }
    public StatusType getStatus() { return status; }
    public void setObjectOfOrder(T objectOfOrder) { this.objectOfOrder = objectOfOrder; }
    public void setStatus(StatusType status) { this.status = status; }

    public void input() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //приходится использовать рефлексию, чтобы создать объект типа T
        Class<T> clazz = (Class<T>)this.objectOfOrder.getClass();
        T objectOfOrder = clazz.getDeclaredConstructor().newInstance();
        StatusType status;

        Scanner scan = new Scanner(System.in);

        System.out.println("\tВвод параметров объекта заказа");
        objectOfOrder.input();
        System.out.print("Введите статус заказа (0 - в ожидании, 1 - в ремонте, 2 - отремонтирован): ");
        status = StatusType.intToStatusType(scan.nextInt());

        this.objectOfOrder = objectOfOrder;
        this.status = status;
    }

    @Override public String toString() {
        return (this.getNum() + ". " + this.objectOfOrder.getName() + "\t" + this.getStatus());
    }
}