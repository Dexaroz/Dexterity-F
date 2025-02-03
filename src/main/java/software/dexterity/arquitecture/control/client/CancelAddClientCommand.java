package software.dexterity.arquitecture.control.client;

import software.dexterity.arquitecture.control.Command;
import software.dexterity.arquitecture.view.client.ClientFormDialog;

public class CancelAddClientCommand implements Command {

    private final ClientFormDialog clientFormDialog;

    public CancelAddClientCommand(ClientFormDialog clientFormDialog) {
        this.clientFormDialog = clientFormDialog;
    }

    @Override
    public void execute() {
        clientFormDialog.close();
    }
}
