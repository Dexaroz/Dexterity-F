package software.dexterity.arquitecture.view.item;

import software.dexterity.arquitecture.control.Command;
import software.dexterity.arquitecture.model.managers.ItemManager;

import java.util.Map;

public interface ItemFormDialogFactory {
    ItemFormDialog createItemFormDialog(Object parentFrame, ItemManager itemManager, Map<String, Command> commands);
}
