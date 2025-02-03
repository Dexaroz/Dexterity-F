package software.dexterity.arquitecture.control.client;

import software.dexterity.arquitecture.control.Command;
import software.dexterity.arquitecture.model.managers.ClientManager;
import software.dexterity.arquitecture.view.client.ClientFormDialogFactory;

import java.util.Map;

public class AddClientCommand implements Command {

    private final Object parentFrame;
    private final ClientManager clientManager;
    private final ClientFormDialogFactory clientFormDialogFactory;
    private final Map<String, Command> commands;

    public AddClientCommand(Object parentFrame, ClientManager clientManager, ClientFormDialogFactory clientFormDialogFactory, Map<String, Command> commands) {
        this.parentFrame = parentFrame;
        this.clientManager = clientManager;
        this.clientFormDialogFactory = clientFormDialogFactory;
        this.commands = commands;
    }

    @Override
    public void execute() {
        clientFormDialogFactory.createClientFormDialog(parentFrame, clientManager, commands).open();
    }
}
