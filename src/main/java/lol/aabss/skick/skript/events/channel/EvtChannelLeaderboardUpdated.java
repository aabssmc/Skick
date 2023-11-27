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
import uk.co.mistyknives.kick4j.events.impl.channel.ChannelLeaderboardUpdatedEvent;
import uk.co.mistyknives.kick4j.events.impl.data.EventChannel;

import javax.annotation.Nullable;

import static lol.aabss.skick.Skick.client;

public class EvtChannelLeaderboardUpdated extends SkriptEvent {

    static{
        Skript.registerEvent("on kick channel leaderboard updated", EvtChannelLeaderboardUpdated.class, ChannelLeaderboardUpdatedBukkit.class,
                "[s]kick [channel] leader[board] update[d]"
        );
        EventValues.registerEventValue(ChannelLeaderboardUpdatedBukkit.class, Kick4J.class, new Getter<>() {
            @Override
            public Kick4J get(ChannelLeaderboardUpdatedBukkit e) {
                return e.getEvent().getClient();
            }
        }, 0);
        EventValues.registerEventValue(ChannelLeaderboardUpdatedBukkit.class, EventChannel.class, new Getter<>() {
            @Override
            public EventChannel get(ChannelLeaderboardUpdatedBukkit e) {
                return e.getEvent().getChannel();
            }
        }, 0);
        EventValues.registerEventValue(ChannelLeaderboardUpdatedBukkit.class, ChannelLeaderboardUpdatedEvent.Leaderboard[].class, new Getter<>() {
            @Override
            public ChannelLeaderboardUpdatedEvent.Leaderboard[] get(ChannelLeaderboardUpdatedBukkit e) {
                return e.getEvent().getLeaderboard();
            }
        }, 0);
        EventValues.registerEventValue(ChannelLeaderboardUpdatedBukkit.class, Integer.class, new Getter<>() {
            @Override
            public Integer get(ChannelLeaderboardUpdatedBukkit e) {
                return e.getEvent().getGiftedQuantity();
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
        return "on kick channel leaderboard updated event";
    }
}
