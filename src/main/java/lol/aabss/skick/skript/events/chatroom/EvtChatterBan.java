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
import uk.co.mistyknives.kick4j.events.impl.chatroom.ChatterBanEvent;
import uk.co.mistyknives.kick4j.events.impl.data.EventChannel;

import javax.annotation.Nullable;

import static lol.aabss.skick.Skick.client;

public class EvtChatterBan extends SkriptEvent {

    static{
        Skript.registerEvent("on kick chatter ban", EvtChatterBan.class, ChatterBanBukkit.class,
                "[s]kick [chat[room]] chatter ban[ned]"
        );
        EventValues.registerEventValue(ChatterBanBukkit.class, Kick4J.class, new Getter<>() {
            @Override
            public Kick4J get(ChatterBanBukkit e) {
                return e.getEvent().getClient();
            }
        }, 0);
        EventValues.registerEventValue(ChatterBanBukkit.class, EventChannel.class, new Getter<>() {
            @Override
            public EventChannel get(ChatterBanBukkit e) {
                return e.getEvent().getChannel();
            }
        }, 0);
        EventValues.registerEventValue(ChatterBanBukkit.class, ChatterBanEvent.User.class, new Getter<>() {
            @Override
            public ChatterBanEvent.User get(ChatterBanBukkit e) {
                return e.getEvent().getBanned();
            }
        }, 0);
    }

    @Override
    public boolean init(@NotNull Literal<?>[] args, int matchedPattern, @NotNull SkriptParser.ParseResult parseResult) {
        if (client == null){
            Skript.error("Kick isn't logged in. (see SecKickLogin)");
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
        return "on kick chatter ban event";
    }
}
