package software.dexterity.arquitecture.control;

import software.dexterity.app.swing.MainFrame;

public class ItemsContentCommand implements Command {

    private final MainFrame mainFrame;

    public ItemsContentCommand(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void execute() {
        mainFrame.showItemContent();
    }
}
