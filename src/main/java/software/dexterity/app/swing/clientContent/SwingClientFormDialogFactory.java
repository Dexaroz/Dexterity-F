package software.dexterity.app.swing.clientContent;

import software.dexterity.arquitecture.model.managers.ClientManager;
import software.dexterity.arquitecture.view.client.ClientFormDialog;
import software.dexterity.arquitecture.view.client.ClientFormDialogFactory;

import javax.swing.*;

public class SwingClientFormDialogFactory implements ClientFormDialogFactory {
    @Override
    public ClientFormDialog createClientFormDialog(Object parentFrame, ClientManager clientManager) {
        return new SwingClientDialog((JFrame) parentFrame, clientManager);
    }
}
