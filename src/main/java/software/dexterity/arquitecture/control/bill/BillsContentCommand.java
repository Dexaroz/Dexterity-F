package software.dexterity.arquitecture.control.bill;

import software.dexterity.app.swing.MainFrame;
import software.dexterity.arquitecture.control.Command;

public class BillsContentCommand implements Command {

    private final MainFrame mainFrame;

    public BillsContentCommand(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void execute() {
        mainFrame.showBillContent();
    }
}
