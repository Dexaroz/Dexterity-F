package software.dexterity.arquitecture.model;

import java.util.concurrent.atomic.AtomicInteger;

public record Item(int id, String name, String description, double pricePerUnit) {
    private static final AtomicInteger ID_ITEM = new AtomicInteger(1);

    public Item(String name, String description, double pricePerUnit){
        this(ID_ITEM.getAndIncrement(), name, description, pricePerUnit);
    }

    public Item editItem(String name, String description, double pricePerUnit){
        return new Item(this.id, name, description, pricePerUnit);
    }
}
