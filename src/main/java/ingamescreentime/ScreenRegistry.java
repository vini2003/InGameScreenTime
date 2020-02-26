package ingamescreentime;

import spinnery.client.InGameHudScreen;
import spinnery.widget.WInterface;
import spinnery.widget.WStaticText;
import spinnery.widget.api.Position;

public class ScreenRegistry {
    public static WStaticText INFO_TEXT_DATE = new WStaticText();
    public static WStaticText INFO_TEXT_TIME = new WStaticText();

    public static void initialize() {
        InGameHudScreen.addOnInitialize(() -> {
            WInterface mainInterface = InGameHudScreen.getInterface();
            INFO_TEXT_DATE = mainInterface.createChild(WStaticText.class, Position.of(4, 4, 0));
            INFO_TEXT_TIME = mainInterface.createChild(WStaticText.class, Position.of(4, INFO_TEXT_DATE.getHeight() + 6, 0));
        });
    }
}
