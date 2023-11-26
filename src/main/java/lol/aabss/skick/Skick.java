package lol.aabss.skick;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import uk.co.mistyknives.kick4j.Kick4J;

import java.io.IOException;

public final class Skick extends JavaPlugin {

    @Getter
    public static Kick4J client;

    @Getter
    private static Skick instance;

    @Getter
    private SkriptAddon addon;

    public void onEnable() {
        instance = this;
        try {
            addon = Skript.registerAddon(this)
                    .loadClasses("lol.aabss.skick", "skript")
                    .setLanguageFileDirectory("lang");
        } catch (IOException e) {
            e.printStackTrace();
        }
//		Metrics metrics = new Metrics(this, xxx);
        getLogger().info("Skick has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
