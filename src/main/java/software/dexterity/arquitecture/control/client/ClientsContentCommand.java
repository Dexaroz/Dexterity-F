package software.dexterity.arquitecture.control.client;

import software.dexterity.app.swing.MainFrame;
import software.dexterity.arquitecture.control.Command;

public class ClientsContentCommand implements Command {

    private final MainFrame mainFrame;

    public ClientsContentCommand(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void execute() {
        mainFrame.showClientContent();
    }
}
