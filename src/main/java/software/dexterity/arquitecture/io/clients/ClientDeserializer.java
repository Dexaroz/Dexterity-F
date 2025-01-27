package software.dexterity.arquitecture.io.clients;

import software.dexterity.arquitecture.model.Client;

public interface ClientDeserializer {
    Client deserialize(String line);
}
