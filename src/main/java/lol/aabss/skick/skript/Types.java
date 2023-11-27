package lol.aabss.skick.skript;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import lol.aabss.skick.events.chatroom.ChannelPollUpdatedBukkit;
import org.jetbrains.annotations.NotNull;
import uk.co.mistyknives.kick4j.Kick4J;
import uk.co.mistyknives.kick4j.events.impl.channel.ChannelLeaderboardUpdatedEvent;
import uk.co.mistyknives.kick4j.events.impl.channel.ChannelSentRaidEvent;
import uk.co.mistyknives.kick4j.events.impl.chatroom.*;
import uk.co.mistyknives.kick4j.events.impl.data.EventChannel;
import uk.co.mistyknives.kick4j.events.impl.data.EventLivestream;

public class Types {
    static{
        Classes.registerClass(new ClassInfo<>(EventChannel.class, "kickeventchannel")
                .user("kick ?event ?channels?")
                .name("kick event channel")
                .description("Represents a Kick event channel.")
                .since("1.0")
                .defaultExpression(new EventValueExpression<>(EventChannel.class))
                .parser(new Parser<EventChannel>() {

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return true;
                    }

                    @Override
                    public @NotNull String toVariableNameString(EventChannel channel) {
                        return channel.getSlug();
                    }

                    @Override
                    public @NotNull String toString(EventChannel channel, int flags) {
                        return toVariableNameString(channel);
                    }
                })
        );

        Classes.registerClass(new ClassInfo<>(EventLivestream.class, "kickeventlivestream")
                .user("kick ?event ?livestreams?")
                .name("kick event livestream")
                .description("Represents a Kick event livestream.")
                .since("1.0")
                .defaultExpression(new EventValueExpression<>(EventLivestream.class))
                .parser(new Parser<EventLivestream>() {

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return true;
                    }

                    @Override
                    public @NotNull String toVariableNameString(EventLivestream stream) {
                        return stream.getSessionTitle();
                    }

                    @Override
                    public @NotNull String toString(EventLivestream stream, int flags) {
                        return toVariableNameString(stream);
                    }
                })
        );

        Classes.registerClass(new ClassInfo<>(Kick4J.class, "kickclient")
                .user("kick ?clients?")
                .name("kick client")
                .description("Represents a Kick client.")
                .since("1.0")
                .defaultExpression(new EventValueExpression<>(Kick4J.class))
                .parser(new Parser<Kick4J>() {

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return true;
                    }

                    @Override
                    public @NotNull String toVariableNameString(Kick4J client) {
                        return client.toString();
                    }

                    @Override
                    public @NotNull String toString(Kick4J client, int flags) {
                        return toVariableNameString(client);
                    }
                })
        );

        Classes.registerClass(new ClassInfo<>(ChannelLeaderboardUpdatedEvent.Leaderboard.class, "kickleaderboard")
                .user("kick ?learboards?")
                .name("kick leaderboard")
                .description("Represents a Kick leaderboard.")
                .since("1.0")
                .defaultExpression(new EventValueExpression<>(ChannelLeaderboardUpdatedEvent.Leaderboard.class))
                .parser(new Parser<ChannelLeaderboardUpdatedEvent.Leaderboard>() {

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return true;
                    }

                    @Override
                    public @NotNull String toVariableNameString(ChannelLeaderboardUpdatedEvent.Leaderboard board) {
                        return board.toString();
                    }

                    @Override
                    public @NotNull String toString(ChannelLeaderboardUpdatedEvent.Leaderboard board, int flags) {
                        return toVariableNameString(board);
                    }
                })
        );
        Classes.registerClass(new ClassInfo<>(ChannelSentRaidEvent.Hosted.class, "kickhosted")
                .user("kick ?hosteds?")
                .name("kick hosted")
                .description("Represents a Kick hoster.")
                .since("1.0")
                .defaultExpression(new EventValueExpression<>(ChannelSentRaidEvent.Hosted.class))
                .parser(new Parser<ChannelSentRaidEvent.Hosted>() {

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return true;
                    }

                    @Override
                    public @NotNull String toVariableNameString(ChannelSentRaidEvent.Hosted host) {
                        return host.toString();
                    }

                    @Override
                    public @NotNull String toString(ChannelSentRaidEvent.Hosted host, int flags) {
                        return toVariableNameString(host);
                    }
                })
        );
        Classes.registerClass(new ClassInfo<>(ChannelPollUpdatedEvent.Poll.class, "kickpoll")
                .user("kick ?polls?")
                .name("kick poll")
                .description("Represents a Kick poll.")
                .since("1.0")
                .defaultExpression(new EventValueExpression<>(ChannelPollUpdatedEvent.Poll.class))
                .parser(new Parser<ChannelPollUpdatedEvent.Poll>() {

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return true;
                    }

                    @Override
                    public @NotNull String toVariableNameString(ChannelPollUpdatedEvent.Poll poll) {
                        return poll.getTitle();
                    }

                    @Override
                    public @NotNull String toString(ChannelPollUpdatedEvent.Poll poll, int flags) {
                        return toVariableNameString(poll);
                    }
                })
        );
        Classes.registerClass(new ClassInfo<>(ChatMessageEvent.Sender.class, "kickmessagesender")
                .user("kick ?message ?senders?")
                .name("kick message sender")
                .description("Represents a Kick message sender.")
                .since("1.0")
                .defaultExpression(new EventValueExpression<>(ChatMessageEvent.Sender.class))
                .parser(new Parser<ChatMessageEvent.Sender>() {

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return true;
                    }

                    @Override
                    public @NotNull String toVariableNameString(ChatMessageEvent.Sender sender) {
                        return sender.getSlug();
                    }

                    @Override
                    public @NotNull String toString(ChatMessageEvent.Sender sender, int flags) {
                        return toVariableNameString(sender);
                    }
                })
        );
        Classes.registerClass(new ClassInfo<>(ChatterBanEvent.User.class, "kickbanned")
                .user("kick ?banneds?")
                .name("kick banned")
                .description("Represents a Kick banned user.")
                .since("1.0")
                .defaultExpression(new EventValueExpression<>(ChatterBanEvent.User.class))
                .parser(new Parser<ChatterBanEvent.User>() {

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return true;
                    }

                    @Override
                    public @NotNull String toVariableNameString(ChatterBanEvent.User banned) {
                        return banned.getSlug();
                    }

                    @Override
                    public @NotNull String toString(ChatterBanEvent.User banned, int flags) {
                        return toVariableNameString(banned);
                    }
                })
        );
        Classes.registerClass(new ClassInfo<>(ChatterUnbanEvent.User.class, "kickunbanned")
                .user("kick ?unbanneds?")
                .name("kick unbanned")
                .description("Represents a Kick unbanned user.")
                .since("1.0")
                .defaultExpression(new EventValueExpression<>(ChatterUnbanEvent.User.class))
                .parser(new Parser<ChatterUnbanEvent.User>() {

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return true;
                    }

                    @Override
                    public @NotNull String toVariableNameString(ChatterUnbanEvent.User unbanned) {
                        return unbanned.getSlug();
                    }

                    @Override
                    public @NotNull String toString(ChatterUnbanEvent.User unbanned, int flags) {
                        return toVariableNameString(unbanned);
                    }
                })
        );
        Classes.registerClass(new ClassInfo<>(PinnedMessageEvent.Message.class, "kickpinmessage")
                .user("kick ?pinned ?message?")
                .name("kick pineed message")
                .description("Represents a Kick pinned message.")
                .since("1.0")
                .defaultExpression(new EventValueExpression<>(PinnedMessageEvent.Message.class))
                .parser(new Parser<PinnedMessageEvent.Message>() {

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return true;
                    }

                    @Override
                    public @NotNull String toVariableNameString(PinnedMessageEvent.Message msg) {
                        return msg.getContent();
                    }

                    @Override
                    public @NotNull String toString(PinnedMessageEvent.Message msg, int flags) {
                        return toVariableNameString(msg);
                    }
                })
        );

    }
}
