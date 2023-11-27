package lol.aabss.skick.skript.expressions.channel;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;
import lol.aabss.skick.events.channel.ChannelGiftedSubscriptionsBukkit;
import lol.aabss.skick.events.channel.ChannelLeaderboardUpdatedBukkit;
import lol.aabss.skick.events.channel.ChannelSubscriptionBukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import uk.co.mistyknives.kick4j.events.impl.data.EventChannel;

import javax.annotation.Nullable;

import static lol.aabss.skick.Skick.client;

public class ExprGifter extends SimpleExpression<EventChannel> {

    static{
        Skript.registerExpression(ExprGifter.class, EventChannel.class, ExpressionType.SIMPLE,
                "[the] [event-]gifter"
        );
    }

    @Override
    protected @Nullable EventChannel[] get(@NotNull Event e) {
        if (e instanceof ChannelGiftedSubscriptionsBukkit){
            return new EventChannel[]{((ChannelGiftedSubscriptionsBukkit) e).getEvent().getGifter()};
        }
        else if (e instanceof ChannelLeaderboardUpdatedBukkit){
            return new EventChannel[]{((ChannelLeaderboardUpdatedBukkit) e).getEvent().getGifter()};
        }
        else if (e instanceof ChannelSubscriptionBukkit){
            return new EventChannel[]{((ChannelSubscriptionBukkit) e).getEvent().getGifter()};
        }
        return new EventChannel[0];
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
        return "the gifter";
    }

    @Override
    public boolean init(@NotNull Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull SkriptParser.ParseResult parseResult) {
        if (client == null){
            Skript.error("Kick isn't logged in. (see SecKickLogin)");
            return false;
        }
        if (!getParser().isCurrentEvent(ChannelGiftedSubscriptionsBukkit.class,
                ChannelSubscriptionBukkit.class,
                ChannelLeaderboardUpdatedBukkit.class
        )) {
            Skript.error("Cannot use 'gifter' outside of a sub events", ErrorQuality.SEMANTIC_ERROR);
            return false;
        }
        return true;
    }
}
