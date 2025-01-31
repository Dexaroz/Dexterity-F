package software.dexterity.arquitecture.control;

import software.dexterity.app.swing.MainFrame;

public class HomeContentCommand implements Command {

    private final MainFrame mainFrame;

    public HomeContentCommand(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void execute() {
        mainFrame.showHomeContent();
    }
}
