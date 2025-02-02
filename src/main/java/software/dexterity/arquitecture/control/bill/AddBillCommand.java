package software.dexterity.arquitecture.control.bill;

import software.dexterity.arquitecture.control.Command;
import software.dexterity.arquitecture.model.managers.BillManager;
import software.dexterity.arquitecture.view.bill.BillFormDialogFactory;

public class AddBillCommand implements Command {

    private final Object parentFrame;
    private final BillManager billManager;
    private final BillFormDialogFactory billFormDialogFactory;

    public AddBillCommand(Object parentFrame, BillManager billManager, BillFormDialogFactory billFormDialogFactory) {
        this.parentFrame = parentFrame;
        this.billManager = billManager;
        this.billFormDialogFactory = billFormDialogFactory;
    }

    @Override
    public void execute() {
        billFormDialogFactory.createBillFormDiallog(parentFrame, billManager).open();
    }
}
