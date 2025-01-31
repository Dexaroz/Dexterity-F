package software.dexterity.app.swing;

import software.dexterity.app.swing.billsContent.SwingBillContent;
import software.dexterity.app.swing.clientContent.SwingClientContent;
import software.dexterity.app.swing.homeContent.SwingHomeContent;
import software.dexterity.app.swing.itemsContent.SwingItemsContent;
import software.dexterity.app.swing.support.DarkGoldPalette;
import software.dexterity.app.swing.topbar.SwingTopbarComponent;
import software.dexterity.arquitecture.control.*;
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

    public void showHomeContent() {
        updateContent(new SwingHomeContent(billManager));
    }

    public void showClientContent() {
        updateContent(new SwingClientContent(clientManager));
    }

    public void showBillContent() {
        updateContent(new SwingBillContent(billManager));
    }

    public void showItemContent() {
        updateContent(new SwingItemsContent(itemManager));
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
        topbarComponent.addButton("Bills", new ClientContentCommand(this));
        topbarComponent.addButton("Clients", new BillsContentCommand(this));
        topbarComponent.addButton("Items", new ItemsContentCommand(this));
        return topbarComponent;
    }
}
