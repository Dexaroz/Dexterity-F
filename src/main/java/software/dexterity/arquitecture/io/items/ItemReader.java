package software.dexterity.arquitecture.io.items;

import software.dexterity.arquitecture.model.Item;

import java.io.IOException;

public interface ItemReader extends AutoCloseable {
    Item read() throws IOException;
}
