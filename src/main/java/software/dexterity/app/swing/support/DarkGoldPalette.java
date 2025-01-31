package software.dexterity.app.swing.support;

import java.awt.*;

public enum DarkGoldPalette {
    Background(24,22,16),
    TextColor(255, 255, 255),
    ButtonBackground(57,52,40),
    SearchInputBackground(57,52,40),
    BorderLineTable(57,52,40),
    TextInput(57,52,40),
    BackgroundHeaderBackgroundTable(39,36,26),
    ButtonBackgroundGold(244,180,0);

    private final Color color;

    DarkGoldPalette(int red, int green, int blue) {
        this.color = new Color(red, green, blue);
    }

    public Color getColor() {
        return color;
    }
}
