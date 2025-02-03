package software.dexterity.app.swing.clientContent;

import software.dexterity.arquitecture.control.Command;
import software.dexterity.arquitecture.model.managers.ClientManager;
import software.dexterity.arquitecture.view.client.ClientFormDialog;
import software.dexterity.arquitecture.view.client.ClientFormDialogFactory;

import javax.swing.*;
import java.util.Map;

public class SwingClientFormDialogFactory implements ClientFormDialogFactory {
    @Override
    public ClientFormDialog createClientFormDialog(Object parentFrame, ClientManager clientManager, Map<String, Command> commands) {
        return new SwingClientDialog((JFrame) parentFrame, clientManager, commands);
    }
}
