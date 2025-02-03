package software.dexterity.arquitecture.control.bill;

import software.dexterity.arquitecture.control.Command;
import software.dexterity.arquitecture.model.managers.BillManager;
import software.dexterity.arquitecture.view.bill.BillFormDialogFactory;

import java.util.Map;

public class AddBillCommand implements Command {

    private final Object parentFrame;
    private final BillManager billManager;
    private final BillFormDialogFactory billFormDialogFactory;
    private final Map<String, Command> commands;

    public AddBillCommand(Object parentFrame, BillManager billManager, BillFormDialogFactory billFormDialogFactory, Map<String, Command> commands) {
        this.parentFrame = parentFrame;
        this.billManager = billManager;
        this.billFormDialogFactory = billFormDialogFactory;
        this.commands = commands;
    }

    @Override
    public void execute() {
        billFormDialogFactory.createBillFormDiallog(parentFrame, billManager, commands).open();
    }
}
