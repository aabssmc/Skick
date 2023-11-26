package lol.aabss.skick.events.channel;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import uk.co.mistyknives.kick4j.events.impl.channel.ChannelGiftedSubscriptionsEvent;

@Getter
public class ChannelGiftedSubscriptionsBukkit extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final ChannelGiftedSubscriptionsEvent event;


    public ChannelGiftedSubscriptionsBukkit(ChannelGiftedSubscriptionsEvent event) {
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