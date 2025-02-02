package software.dexterity.arquitecture.view.client;

import software.dexterity.arquitecture.model.managers.ClientManager;

public interface ClientFormDialogFactory {
    ClientFormDialog createClientFormDialog(Object parentFrame, ClientManager clientManager);
}
