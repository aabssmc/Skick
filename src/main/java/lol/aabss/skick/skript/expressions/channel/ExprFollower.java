package lol.aabss.skick.skript.expressions.channel;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;
import lol.aabss.skick.events.channel.ChannelFollowBukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import uk.co.mistyknives.kick4j.events.impl.data.EventChannel;

import javax.annotation.Nullable;

public class ExprFollower extends SimpleExpression<EventChannel> {

    static{
        Skript.registerExpression(ExprFollower.class, EventChannel.class, ExpressionType.SIMPLE,
                "[the] [event-]follower"
        );
    }

    @Override
    protected @Nullable EventChannel[] get(@NotNull Event e) {
        return new EventChannel[]{((ChannelFollowBukkit) e).getEvent().getFollower()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @NotNull
    @Override
    public Class<? extends EventChannel> getReturnType() {
        return EventChannel.class;
    }

    @NotNull
    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "the follower";
    }

    @Override
    public boolean init(@NotNull Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull SkriptParser.ParseResult parseResult) {
        if (!getParser().isCurrentEvent(ChannelFollowBukkit.class)) {
            Skript.error("Cannot use 'follower' outside of a channel follow event", ErrorQuality.SEMANTIC_ERROR);
            return false;
        }
        return true;
    }
}
