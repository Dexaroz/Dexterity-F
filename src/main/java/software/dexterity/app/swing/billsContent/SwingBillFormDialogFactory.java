package software.dexterity.app.swing.billsContent;

import software.dexterity.arquitecture.model.managers.BillManager;
import software.dexterity.arquitecture.view.bill.BillFormDialog;
import software.dexterity.arquitecture.view.bill.BillFormDialogFactory;

import javax.swing.*;

public class SwingBillFormDialogFactory implements BillFormDialogFactory  {
    @Override
    public BillFormDialog createBillFormDiallog(Object parentFrame, BillManager billManager) {
        return new SwingBillDialog((JFrame) parentFrame, billManager);
    }
}
