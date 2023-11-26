package lol.aabss.skick.events.chatroom;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import uk.co.mistyknives.kick4j.events.impl.chatroom.GiftedSubscriptionsEvent;

@Getter
public class GiftedSubscriptionsBukkit extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final GiftedSubscriptionsEvent event;


    public GiftedSubscriptionsBukkit(GiftedSubscriptionsEvent event) {
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
