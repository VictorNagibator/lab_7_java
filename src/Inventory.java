import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import java.util.Comparator;

public class Inventory {
    //здесь для разнообразия использую связные списки
    private LinkedList<OrderComponent> items = new LinkedList<>();

    public Inventory() { }

    public int getItemCount() {
        return items.size();
    }

    public void addItem(OrderComponent item) {
        items.add(item);
    }

    public void deleteItem(String name) {
        items.remove(searchComponent(name));
    }

    public void sortByComponentName() {
        Collections.sort(items, Comparator.comparing(OrderComponent::getObjectName));
    }

    public void sortByName() {
        Collections.sort(items, Comparator.comparing(OrderComponent::getName));
    }

    public OrderComponent searchComponent(String name) {
        for (OrderComponent item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Компонент не найден!");
    }

    public void print() {
        System.out.println("Список компонентов:");
        for (OrderComponent item : items) {
            System.out.println(item.getObjectName() + ": " + item.getName());
        }
    }

    public Set<String> getTypesOfComponents() {
        Set<String> uniqueNames = new HashSet<>();
        for (OrderComponent item : items) {
            uniqueNames.add(item.getObjectName());
        }
        return uniqueNames;
    }
}