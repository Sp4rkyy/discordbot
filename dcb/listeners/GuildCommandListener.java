package dcb.listeners;

import dcb.handler.GuildCommandHandler;
import dcb.util.BlackListUtility;
import dcb.util.Config;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildCommandListener extends ListenerAdapter {

    private Config config;

    public GuildCommandListener(){
        config = new Config();
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if(!event.getAuthor().isBot() && event.getMessage().getContentRaw().startsWith(config.load("bot_command_indicator")) && !new BlackListUtility().isincluded(event.getChannel().getId())){   // modules should not interfere with default commands nor should the channel be included on the blacklist
            System.out.println("[INFO][GUILD][CMD] "+event.getAuthor()+" >> "+event.getMessage().getContentRaw());
            new Thread(new GuildCommandHandler(event)).start();
        }
    }
}
