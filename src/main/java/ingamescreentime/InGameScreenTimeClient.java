package ingamescreentime;

import net.fabricmc.api.ClientModInitializer;

public class InGameScreenTimeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.initialize();
        CallbackRegistry.initialize();
        KeybindRegistry.initialize();
    }
}
