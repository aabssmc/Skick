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
import uk.co.mistyknives.kick4j.events.impl.data.EventLivestream;

import javax.annotation.Nullable;

import static lol.aabss.skick.Skick.client;


//TODO: add expressions to get elements in a event livestream
public class EvtChannelWentLive extends SkriptEvent {

    static{
        Skript.registerEvent("on kick channel went live", EvtChannelWentLive.class, ChannelWentLiveBukkit.class,
                "[s]kick [channel] [went|goes] live",
                "[s]kick [channel] [start|begin] stream"
        );
        EventValues.registerEventValue(ChannelWentLiveBukkit.class, Kick4J.class, new Getter<>() {
            @Override
            public Kick4J get(ChannelWentLiveBukkit e) {
                return e.getEvent().getClient();
            }
        }, 0);
        EventValues.registerEventValue(ChannelWentLiveBukkit.class, EventChannel.class, new Getter<>() {
            @Override
            public EventChannel get(ChannelWentLiveBukkit e) {
                return e.getEvent().getChannel();
            }
        }, 0);
        EventValues.registerEventValue(ChannelWentLiveBukkit.class, EventLivestream.class, new Getter<>() {
            @Override
            public EventLivestream get(ChannelWentLiveBukkit e) {
                return e.getEvent().getLivestream();
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
        return "on kick channel went live event";
    }
}
