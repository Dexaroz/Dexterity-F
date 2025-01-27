package software.dexterity.arquitecture.io.clients;

import software.dexterity.arquitecture.model.Bill;
import software.dexterity.arquitecture.model.Client;

import java.io.IOException;

public interface ClientReader extends AutoCloseable {
    Client read() throws IOException;
}
