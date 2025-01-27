package software.dexterity.arquitecture.io.bills;

import software.dexterity.arquitecture.model.Bill;

import java.io.IOException;

public interface BillWriter extends AutoCloseable {
    void write(Bill bill) throws IOException;
}
