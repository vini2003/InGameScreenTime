package ingamescreentime;

import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

public class CallbackRegistry {
    public static final float DAY = 24000.0F;
    public static final float HOUR = 1000.0F;
    public static final float MINUTE = 16.66F;

    public static final String DATE_KEY = "text.ingamescreentime.date";
    public static final String TIME_KEY = "text.ingamescreentime.time";
    public static final String BIOME_KEY = "text.ingamescreentime.biome";

    public static void initialize() {
        ClientTickCallback.EVENT.register(tick -> {
            World world = tick.world;
            PlayerEntity player = tick.player;

            if (world != null && player != null) {
                long time = (long) (world.getTime() / DAY) + 1;

                long timeOfDay = (long) (world.getTimeOfDay() % DAY);

                long hour = (long) (timeOfDay / HOUR);
                long minute = (long) ((timeOfDay % HOUR) / MINUTE);

                boolean mode;

                final boolean AM = false;
                final boolean PM = true;

                if (hour + 6 > 12 && hour + 6 < 24) {
                    mode = PM;
                    hour = Math.max(1, hour + 6 - 12);
                } else if (hour + 6 == 24) {
                    mode = AM;
                    hour = 0;
                } else if (hour + 6 == 12) {
                    mode = PM;
                    hour = 12;
                } else {
                    mode = AM;
                    hour = hour + 6;
                }

                ScreenRegistry.INFO_TEXT_DATE.setText(new TranslatableText(DATE_KEY).append(": " + time));
                ScreenRegistry.INFO_TEXT_TIME.setText(new TranslatableText(TIME_KEY).append(": " + hour + ":" + (minute > 9 ? minute : "0" + minute) + (!mode ? "AM" : "PM")));
                ScreenRegistry.INFO_TEXT_BIOME.setText(new TranslatableText(BIOME_KEY).append(": " + world.getBiome(player.getBlockPos()).getName().asFormattedString()));
            }
        });
    }
}
