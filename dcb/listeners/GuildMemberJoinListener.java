package dcb.listeners;

import dcb.handler.GuildMemberJoinHandler;
import dcb.util.Config;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildMemberJoinListener extends ListenerAdapter {

    private Config config;

    public GuildMemberJoinListener() {
        config = new Config();
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event){
        if(!event.getUser().isBot() && Boolean.parseBoolean(config.load("bot_sayhellotonew"))){
            new Thread(new GuildMemberJoinHandler(event)).start();
        }
    }
}
