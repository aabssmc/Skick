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
import uk.co.mistyknives.kick4j.events.impl.channel.ChannelSentRaidEvent;
import uk.co.mistyknives.kick4j.events.impl.data.EventChannel;

import javax.annotation.Nullable;

import static lol.aabss.skick.Skick.client;

public class EvtChannelSentRaid extends SkriptEvent {

    static{
        Skript.registerEvent("on kick channel sent raid", EvtChannelSentRaid.class, ChannelSentRaidBukkit.class,
                "[s]kick [channel] sen(t|d) raid",
                "[s]kick [channel] raid sen(t|d)"
        );
        EventValues.registerEventValue(ChannelSentRaidBukkit.class, Kick4J.class, new Getter<>() {
            @Override
            public Kick4J get(ChannelSentRaidBukkit e) {
                return e.getEvent().getClient();
            }
        }, 0);
        EventValues.registerEventValue(ChannelSentRaidBukkit.class, EventChannel.class, new Getter<>() {
            @Override
            public EventChannel get(ChannelSentRaidBukkit e) {
                return e.getEvent().getChannel();
            }
        }, 0);
        EventValues.registerEventValue(ChannelSentRaidBukkit.class, ChannelSentRaidEvent.Hosted.class, new Getter<>() {
            @Override
            public ChannelSentRaidEvent.Hosted get(ChannelSentRaidBukkit e) {
                return e.getEvent().getHosted();
            }
        }, 0);
        EventValues.registerEventValue(ChannelSentRaidBukkit.class, String.class, new Getter<>() {
            @Override
            public String get(ChannelSentRaidBukkit e) {
                return e.getEvent().getSlug();
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
        return "on kick channel sent raid event";
    }
}
