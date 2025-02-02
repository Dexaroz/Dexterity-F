package software.dexterity.arquitecture.view.item;

import software.dexterity.arquitecture.model.managers.ItemManager;

public interface ItemFormDialogFactory {
    ItemFormDialog createItemFormDialog(Object parentFrame, ItemManager itemManager);
}
