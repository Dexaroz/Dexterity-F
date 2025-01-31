package software.dexterity.arquitecture.control;

import software.dexterity.app.swing.MainFrame;

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
