package software.dexterity.arquitecture.io.clients;

import software.dexterity.arquitecture.model.Client;

import java.io.IOException;

public interface ClientWriter extends AutoCloseable {
    void write(Client client) throws IOException;
}
