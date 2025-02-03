package software.dexterity.arquitecture.model.managers;

import software.dexterity.arquitecture.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private final List<Item> items;

    public ItemManager() {
        this.items = new ArrayList<>();
    }

    public void addItem(String name, String description, double pricePerUnit){
        items.add(new Item(name, description, pricePerUnit));
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public void editItem(Item item, String name, String description, double pricePerUnit){
        items.remove(item);
        items.add(item.editItem(name, description, pricePerUnit));
    }

    public List<Item> getItems(){
        return items;
    }

    public Item[] getItemsToArray(){
        return items.toArray(new Item[0]);
    }
}
