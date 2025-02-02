package software.dexterity.arquitecture.control.client;

import software.dexterity.arquitecture.control.Command;
import software.dexterity.arquitecture.model.managers.ClientManager;
import software.dexterity.arquitecture.view.client.ClientFormDialogFactory;

public class AddClientCommand implements Command {

    private final Object parentFrame;
    private final ClientManager clientManager;
    private final ClientFormDialogFactory clientFormDialogFactory;

    public AddClientCommand(Object parentFrame, ClientManager clientManager, ClientFormDialogFactory clientFormDialogFactory) {
        this.parentFrame = parentFrame;
        this.clientManager = clientManager;
        this.clientFormDialogFactory = clientFormDialogFactory;
    }

    @Override
    public void execute() {
        clientFormDialogFactory.createClientFormDialog(parentFrame, clientManager).open();
    }
}
