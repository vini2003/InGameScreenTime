package ingamescreentime;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import org.lwjgl.glfw.GLFW;
import spinnery.client.InGameHudScreen;

public class KeybindRegistry {
    public static FabricKeyBinding INFO_KEYBIND = FabricKeyBinding.Builder.create(new Identifier("ingamescreentime", "info"), InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_I, "InGameScreenTime").build();

    public static void initialize() {
        ClientTickCallback.EVENT.register(tick -> {
            if (INFO_KEYBIND.wasPressed()) {
                ScreenRegistry.INFO_TEXT_TIME.setHidden(!ScreenRegistry.INFO_TEXT_TIME.isHidden());
                ScreenRegistry.INFO_TEXT_DATE.setHidden(!ScreenRegistry.INFO_TEXT_DATE.isHidden());
            }
        });
    }

}
