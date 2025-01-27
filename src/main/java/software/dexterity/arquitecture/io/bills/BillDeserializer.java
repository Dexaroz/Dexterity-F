package software.dexterity.arquitecture.io.bills;

import software.dexterity.arquitecture.model.Bill;

public interface BillDeserializer {
    Bill deserialize(String line);
}
