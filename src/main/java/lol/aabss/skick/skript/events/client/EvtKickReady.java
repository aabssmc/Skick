package lol.aabss.skick.skript.events.client;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import lol.aabss.skick.events.channel.ChannelFollowBukkit;
import lol.aabss.skick.events.client.*;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import uk.co.mistyknives.kick4j.Kick4J;

import javax.annotation.Nullable;

import static lol.aabss.skick.Skick.client;

public class EvtKickReady extends SkriptEvent {

    static{
        Skript.registerEvent("on kick ready", EvtKickReady.class, ReadyBukkit.class,
                "[s]kick [client] ready"
        );
        EventValues.registerEventValue(ReadyBukkit.class, Kick4J.class, new Getter<>() {
            @Override
            public Kick4J get(ReadyBukkit e) {
                return e.getEvent().getClient();
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
        return "on kick ready event";
    }
}
