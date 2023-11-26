package lol.aabss.skick.skript.events.channel;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import lol.aabss.skick.events.channel.*;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

import static lol.aabss.skick.Skick.client;

public class EvtChannelSentRaid extends SkriptEvent {

    static{
        Skript.registerEvent("on kick channel sent raid", EvtChannelSentRaid.class, ChannelSentRaidBukkit.class,
                "[s]kick [channel] sen(t|d) raid",
                "[s]kick [channel] raid sen(t|d)"
        );
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
        return "on kick channel sent raid event";
    }
}
