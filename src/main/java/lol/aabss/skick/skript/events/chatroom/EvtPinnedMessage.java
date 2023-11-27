package lol.aabss.skick.skript.events.chatroom;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import lol.aabss.skick.events.chatroom.*;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import uk.co.mistyknives.kick4j.Kick4J;
import uk.co.mistyknives.kick4j.events.impl.chatroom.PinnedMessageEvent;

import javax.annotation.Nullable;

import static lol.aabss.skick.Skick.client;

public class EvtPinnedMessage extends SkriptEvent {

    static{
        Skript.registerEvent("on kick pinned message", EvtPinnedMessage.class, PinnedMessageBukkit.class,
                "[s]kick [chat[room]] pin[ned] message"
        );
        EventValues.registerEventValue(PinnedMessageBukkit.class, Kick4J.class, new Getter<>() {
            @Override
            public Kick4J get(PinnedMessageBukkit e) {
                return e.getEvent().getClient();
            }
        }, 0);
        EventValues.registerEventValue(PinnedMessageBukkit.class, PinnedMessageEvent.Message.class, new Getter<>() {
            @Override
            public PinnedMessageEvent.Message get(PinnedMessageBukkit e) {
                return e.getEvent().getMessage();
            }
        }, 0);
        EventValues.registerEventValue(PinnedMessageBukkit.class, String.class, new Getter<>() {
            @Override
            public String get(PinnedMessageBukkit e) {
                return e.getEvent().getDuration();
            }
        }, 0);
    }

    @Override
    public boolean init(@NotNull Literal<?>[] args, int matchedPattern, @NotNull SkriptParser.ParseResult parseResult) {
        if (client == null){
            Skript.error("Kick isn't logged in. (see EffKickLogin)");
            return false;
        }
        return true;
    }

    @Override
    public boolean check(@NotNull Event e) {
        return true;
    }

    @NotNull
    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "on kick pin message event";
    }
}
