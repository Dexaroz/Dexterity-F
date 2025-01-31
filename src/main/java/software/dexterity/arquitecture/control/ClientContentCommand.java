package software.dexterity.arquitecture.control;

import software.dexterity.app.swing.MainFrame;

public class ClientContentCommand implements Command {

    private final MainFrame mainFrame;

    public ClientContentCommand(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void execute() {
        mainFrame.showClientContent();
    }
}
