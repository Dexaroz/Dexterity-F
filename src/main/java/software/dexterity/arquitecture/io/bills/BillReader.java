package software.dexterity.arquitecture.io.bills;

import software.dexterity.arquitecture.model.Bill;

import java.io.IOException;

public interface BillReader extends AutoCloseable {
    Bill read() throws IOException;
}
