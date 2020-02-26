package ingamescreentime;

import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CallbackRegistry {
    public static void initialize() {
        ClientTickCallback.EVENT.register(tick -> {
            World world = tick.world;
            if (world != null) {
                long time = world.getTime();
                long timeOfDay = world.getTimeOfDay();
                boolean AM = true;
                long hours = timeOfDay / 1000;
                long minutes = timeOfDay % 1000;
                if (hours >= 24) {
                    hours = hours % 24;
                }
                long hour;
                if (hours + 6 > 12) {
                    hour = hours + 6 - 12;
                    AM = false;
                } else {
                    hour = hours + 6;
                }
                long minute = minutes / 60;
                ScreenRegistry.INFO_TEXT_DATE.setText(new TranslatableText("text.ingamescreentime.date").append(": ").append(String.valueOf(time / 24000)));
                ScreenRegistry.INFO_TEXT_TIME.setText(new TranslatableText("text.ingamescreentime.time").append(": ").append(hour + ":" + (minute > 9 ? minute : "0" + minute) + (AM ? " AM" : " PM")));
            }
        });
    }
}
