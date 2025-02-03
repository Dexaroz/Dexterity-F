package software.dexterity.app.swing.itemsContent;

import software.dexterity.arquitecture.control.Command;
import software.dexterity.arquitecture.model.managers.ItemManager;
import software.dexterity.arquitecture.view.item.ItemFormDialog;
import software.dexterity.arquitecture.view.item.ItemFormDialogFactory;

import javax.swing.*;
import java.util.Map;

public class SwingItemFormDialogFactory implements ItemFormDialogFactory {
    @Override
    public ItemFormDialog createItemFormDialog(Object parentFrame, ItemManager itemManager, Map<String, Command> commands) {
        return new SwingItemDialog((JFrame) parentFrame, itemManager, commands);
    }
}
