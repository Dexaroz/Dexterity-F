package software.dexterity.arquitecture.control.item;

import software.dexterity.arquitecture.control.Command;
import software.dexterity.arquitecture.model.managers.ItemManager;
import software.dexterity.arquitecture.view.item.ItemFormDialogFactory;

import java.util.Map;

public class AddItemCommand implements Command  {

    private final Object parentFrame;
    private final ItemManager itemManager;
    private final ItemFormDialogFactory itemFormDialogFactory;
    private final Map<String, Command> commands;

    public AddItemCommand(Object parentFrame, ItemManager itemManager, ItemFormDialogFactory itemFormDialogFactory, Map<String, Command> commands) {
        this.parentFrame = parentFrame;
        this.itemManager = itemManager;
        this.itemFormDialogFactory = itemFormDialogFactory;
        this.commands = commands;
    }

    @Override
    public void execute() {
        itemFormDialogFactory.createItemFormDialog(parentFrame, itemManager, commands).open();
    }
}
