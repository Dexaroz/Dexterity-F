package software.dexterity.arquitecture.view.bill;

import software.dexterity.arquitecture.model.managers.BillManager;

public interface BillFormDialogFactory {
    BillFormDialog createBillFormDiallog(Object parentFrame, BillManager billManager);
}
