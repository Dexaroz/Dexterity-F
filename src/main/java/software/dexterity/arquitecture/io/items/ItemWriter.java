package software.dexterity.arquitecture.io.items;

import software.dexterity.arquitecture.model.Item;

import java.io.IOException;

public interface ItemWriter extends AutoCloseable {
    void write(Item item) throws IOException;
}
