package lol.aabss.skick.events.chatroom;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import uk.co.mistyknives.kick4j.events.impl.chatroom.ChatroomClearedEvent;

@Getter
public class ChatroomClearedBukkit extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final ChatroomClearedEvent event;


    public ChatroomClearedBukkit(ChatroomClearedEvent event) {
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
