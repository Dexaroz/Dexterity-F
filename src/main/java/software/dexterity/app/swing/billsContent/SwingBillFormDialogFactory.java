package software.dexterity.app.swing.billsContent;

import software.dexterity.arquitecture.control.Command;
import software.dexterity.arquitecture.model.managers.BillManager;
import software.dexterity.arquitecture.view.bill.BillFormDialog;
import software.dexterity.arquitecture.view.bill.BillFormDialogFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class SwingBillFormDialogFactory implements BillFormDialogFactory  {
    @Override
    public BillFormDialog createBillFormDiallog(Object parentFrame, BillManager billManager, Map<String, Command> commands) {
        return new SwingBillDialog((JFrame) parentFrame, billManager, commands);
    }
}
