package dcb.handler;

import dcb.modulemanagement.GuildCoreModuleProcessor;
import dcb.modulemanagement.GuildModuleProcessor;
import dcb.util.Config;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class GuildMessageHandler implements Runnable{

    private GuildMessageReceivedEvent event;
    private Config config;

    public GuildMessageHandler(GuildMessageReceivedEvent event){
        this.event = event;
        this.config = new Config();
    }

    @Override
    public void run() {
        boolean finished = false;
        // check if coremodule is activated
        if(Boolean.parseBoolean(config.load("bot_activate_coremodule"))){
            // try to handle with core module
            GuildCoreModuleProcessor cmp = new GuildCoreModuleProcessor(event);
            finished = cmp.handle();
        }
        // if coremodule didnt handle, try all other modules (if modules are activated)
        if(Boolean.parseBoolean(config.load("bot_activate_modules")) && !finished){
            GuildModuleProcessor mp = new GuildModuleProcessor(event);
            finished = mp.handle();
        }
        // if still nothing happend, try nothing else
    }
}
