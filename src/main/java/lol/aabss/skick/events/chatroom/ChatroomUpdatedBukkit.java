package lol.aabss.skick.events.chatroom;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import uk.co.mistyknives.kick4j.events.impl.chatroom.ChatroomUpdatedEvent;

@Getter
public class ChatroomUpdatedBukkit extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final ChatroomUpdatedEvent event;


    public ChatroomUpdatedBukkit(ChatroomUpdatedEvent event) {
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
