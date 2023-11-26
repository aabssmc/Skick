package lol.aabss.skick.events.client;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import uk.co.mistyknives.kick4j.events.impl.client.ReadyEvent;

@Getter
public class ReadyBukkit extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final ReadyEvent event;


    public ReadyBukkit(ReadyEvent event) {
        this.event = event;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
