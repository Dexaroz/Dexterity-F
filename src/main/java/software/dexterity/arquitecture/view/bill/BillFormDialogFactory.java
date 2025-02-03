package software.dexterity.arquitecture.view.bill;

import software.dexterity.arquitecture.control.Command;
import software.dexterity.arquitecture.model.managers.BillManager;

import java.util.Map;

public interface BillFormDialogFactory {
    BillFormDialog createBillFormDiallog(Object parentFrame, BillManager billManager, Map<String, Command> commands);
}
