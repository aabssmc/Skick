package lol.aabss.skick.skript.events.channel;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import lol.aabss.skick.events.channel.*;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import uk.co.mistyknives.kick4j.Kick4J;
import uk.co.mistyknives.kick4j.events.impl.data.EventChannel;

import javax.annotation.Nullable;

import static lol.aabss.skick.Skick.client;

public class EvtSubscription extends SkriptEvent {

    static{
        Skript.registerEvent("on kick channel subscription", EvtSubscription.class, SubscriptionBukkit.class,
                "[s]kick [channel] sub[scription]"
        );
        EventValues.registerEventValue(SubscriptionBukkit.class, Kick4J.class, new Getter<>() {
            @Override
            public Kick4J get(SubscriptionBukkit e) {
                return e.getEvent().getClient();
            }
        }, 0);
        EventValues.registerEventValue(SubscriptionBukkit.class, String.class, new Getter<>() {
            @Override
            public String get(SubscriptionBukkit e) {
                return e.getEvent().getUsername();
            }
        }, 0);
        EventValues.registerEventValue(SubscriptionBukkit.class, Integer.class, new Getter<>() {
            @Override
            public Integer get(SubscriptionBukkit e) {
                return e.getEvent().getMonths();
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
        return "on kick channel subscription event";
    }
}
