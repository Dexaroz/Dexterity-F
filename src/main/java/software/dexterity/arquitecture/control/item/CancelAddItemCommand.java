package software.dexterity.arquitecture.control.item;

import software.dexterity.arquitecture.control.Command;
import software.dexterity.arquitecture.view.item.ItemFormDialog;

public class CancelAddItemCommand implements Command {

    private final ItemFormDialog itemFormDialog;

    public CancelAddItemCommand(ItemFormDialog itemFormDialog) {
        this.itemFormDialog = itemFormDialog;
    }

    @Override
    public void execute() {
        itemFormDialog.close();
    }
}
