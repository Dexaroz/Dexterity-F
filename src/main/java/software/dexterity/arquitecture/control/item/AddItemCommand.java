package software.dexterity.arquitecture.control.item;

import software.dexterity.arquitecture.control.Command;
import software.dexterity.arquitecture.model.managers.ItemManager;
import software.dexterity.arquitecture.view.item.ItemFormDialogFactory;

public class AddItemCommand implements Command  {

    private final Object parentFrame;
    private final ItemManager itemManager;
    private final ItemFormDialogFactory itemFormDialogFactory;

    public AddItemCommand(Object parentFrame, ItemManager itemManager, ItemFormDialogFactory itemFormDialogFactory) {
        this.parentFrame = parentFrame;
        this.itemManager = itemManager;
        this.itemFormDialogFactory = itemFormDialogFactory;
    }

    @Override
    public void execute() {
        itemFormDialogFactory.createItemFormDialog(parentFrame, itemManager).open();
    }
}
