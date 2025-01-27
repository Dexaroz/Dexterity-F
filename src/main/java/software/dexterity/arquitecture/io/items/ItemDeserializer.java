package software.dexterity.arquitecture.io.items;

import software.dexterity.arquitecture.model.Item;

public interface ItemDeserializer {
    Item deserialize(String line);
}
