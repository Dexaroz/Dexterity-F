package software.dexterity.arquitecture.view.client;

import software.dexterity.arquitecture.control.Command;
import software.dexterity.arquitecture.model.managers.ClientManager;

import java.util.Map;

public interface ClientFormDialogFactory {
    ClientFormDialog createClientFormDialog(Object parentFrame, ClientManager clientManager, Map<String, Command> commands);
}
