import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        inventory.addItem(new CPU("Intel Core i7-10700K"));
        inventory.addItem(new GPU("NVIDIA GeForce RTX 3080"));
        inventory.addItem(new RAM("HyperX Fury"));
        inventory.addItem(new Motherboard("ASUS ROG Strix Z490-E Gaming"));
        inventory.addItem(new CPU("AMD Ryzen 9 5900X"));
        inventory.print();
        System.out.println();

        inventory.sortByComponentName();
        inventory.print();
        System.out.println();

        inventory.deleteItem("HyperX Fury");
        inventory.sortByName();
        inventory.print();
        System.out.println();

        System.out.println(inventory.searchComponent("NVIDIA GeForce RTX 3080"));
        System.out.println();

        Set<String> uniqueComponents = inventory.getTypesOfComponents();
        System.out.println("Виды компонентов:");
        for (String component : uniqueComponents) {
            System.out.println(component);
        }
    }
}