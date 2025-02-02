package software.dexterity.arquitecture.control.item;

import software.dexterity.app.swing.MainFrame;
import software.dexterity.arquitecture.control.Command;

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
