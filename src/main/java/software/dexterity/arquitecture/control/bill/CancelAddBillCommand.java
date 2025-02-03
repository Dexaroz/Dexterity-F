package software.dexterity.arquitecture.control.bill;

import software.dexterity.arquitecture.control.Command;
import software.dexterity.arquitecture.view.bill.BillFormDialog;

public class CancelAddBillCommand implements Command {

    private final BillFormDialog billFormDialog;

    public CancelAddBillCommand(BillFormDialog billFormDialog) {
        this.billFormDialog = billFormDialog;
    }

    @Override
    public void execute() {
        billFormDialog.close();
    }
}
