package software.dexterity.arquitecture.view;

import software.dexterity.arquitecture.control.Command;

public interface Topbar {
    void addButton(String label, Command command);
    void setButtonAction(String label, Command command);
}
