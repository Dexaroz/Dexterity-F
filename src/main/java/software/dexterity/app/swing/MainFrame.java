package software.dexterity.app.swing;

import software.dexterity.app.swing.billsContent.SwingBillContent;
import software.dexterity.app.swing.billsContent.SwingBillFormDialogFactory;
import software.dexterity.app.swing.clientContent.SwingClientContent;
import software.dexterity.app.swing.clientContent.SwingClientFormDialogFactory;
import software.dexterity.app.swing.homeContent.SwingHomeContent;
import software.dexterity.app.swing.itemsContent.SwingItemFormDialogFactory;
import software.dexterity.app.swing.itemsContent.SwingItemContent;
import software.dexterity.app.swing.topbar.SwingTopbarComponent;
import software.dexterity.arquitecture.control.*;
import software.dexterity.arquitecture.control.bill.AddBillCommand;
import software.dexterity.arquitecture.control.bill.BillsContentCommand;
import software.dexterity.arquitecture.control.client.AddClientCommand;
import software.dexterity.arquitecture.control.client.ClientsContentCommand;
import software.dexterity.arquitecture.control.item.AddItemCommand;
import software.dexterity.arquitecture.control.item.ItemsContentCommand;
import software.dexterity.arquitecture.model.managers.BillManager;
import software.dexterity.arquitecture.model.managers.ClientManager;
import software.dexterity.arquitecture.model.managers.ItemManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private final Map<String, Command> commands;
    private final BillManager billManager;
    private final ItemManager itemManager;
    private final ClientManager clientManager;

    private JPanel currentContent;

    public MainFrame() throws HeadlessException {
        this.setTitle("Dexterity F");
        this.setSize(new Dimension(1024, 768));
        this.setMinimumSize(new Dimension(1024,768));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        this.commands = new HashMap<>();

        this.billManager = new BillManager();
        this.itemManager = new ItemManager();
        this.clientManager = new ClientManager();

        showHomeContent();

        this.add(createTopbar(), BorderLayout.NORTH);
        this.add(currentContent, BorderLayout.CENTER);
    }

    // Content visualization

    public void showHomeContent() {
        updateContent(new SwingHomeContent(billManager, getHomeCommands()));
    }

    public void showClientContent() {
        updateContent(new SwingClientContent(clientManager, getClientCommands()));
    }

    public void showBillContent() {
        updateContent(new SwingBillContent(billManager, getBillCommands()));
    }

    public void showItemContent() {
        updateContent(new SwingItemContent(itemManager, getItemsCommands()));
    }

    private void updateContent(Component newContent) {
        if (currentContent != null) {
            this.remove(currentContent);
        }

        currentContent = (JPanel) newContent;
        this.add(currentContent, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }

    private Component createTopbar() {
        SwingTopbarComponent topbarComponent = new SwingTopbarComponent("Dexterity F", getClass().getResource("/logo.png"));
        topbarComponent.addButton("Home", new HomeContentCommand(this));
        topbarComponent.addButton("Bills", new BillsContentCommand(this));
        topbarComponent.addButton("Clients", new ClientsContentCommand(this));
        topbarComponent.addButton("Items", new ItemsContentCommand(this));
        return topbarComponent;
    }

    // Commands

    private Map<String, Command> getHomeCommands(){
        return null;
    }

    private Map<String, Command> getBillCommands(){
        Map<String, Command> commands = new HashMap<>();

        commands.put("Add", new AddBillCommand(this, billManager, new SwingBillFormDialogFactory(), getAddBillFormDialogCommands()));
        return commands;
    }

    private Map<String, Command> getClientCommands(){
        Map<String, Command> commands = new HashMap<>();

        commands.put("Add", new AddClientCommand(this, clientManager, new SwingClientFormDialogFactory(), getAddClientFormDialogCommands()));
        return commands;
    }

    private Map<String, Command> getItemsCommands(){
        Map<String, Command> commands = new HashMap<>();

        commands.put("Add", new AddItemCommand(this, itemManager, new SwingItemFormDialogFactory(), getAddItemFormDialogCommands()));
        return commands;
    }

    private Map<String, Command> getAddBillFormDialogCommands() {
        return null;
    }

    private Map<String, Command> getAddClientFormDialogCommands() {
        return null;
    }

    private Map<String, Command> getAddItemFormDialogCommands() {
        return null;
    }

    public BillManager getBillManager(){
        return billManager;
    }

    public ClientManager getClientManager(){
        return clientManager;
    }

    public ItemManager getItemManager(){
        return itemManager;
    }
}
