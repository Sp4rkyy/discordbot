package dcb.handler;

import dcb.commands.guild.*;
import dcb.util.Config;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import java.util.HashMap;

public class GuildCommandHandler implements Runnable {

    private GuildMessageReceivedEvent event;
    private Config config;
    private HashMap<String, GuildCommand> commands = new HashMap<>();

    public GuildCommandHandler(GuildMessageReceivedEvent event){
        this.event = event;
        this.config = new Config();
    }

    @Override
    public void run() {
        // only process if bot could respond
        if(event.getGuild().getSelfMember().hasPermission(event.getChannel(), Permission.MESSAGE_WRITE)){
            // get message
            String message = event.getMessage().getContentRaw();
            // parse arguments from message
            String[] args = getargs(message);
            // get member of author
            Member member = event.getMember();
            // register commands
            registercommands();
            // check if requested command exists
            if(commands.containsKey(args[0])){
                //check if permissions fit
                if(commands.get(args[0]).permission(member)){
                    //execute command
                    commands.get(args[0]).execute(event,member,args);
                }else{
                    event.getChannel().sendMessage("You're not authorized to do that.").queue();
                }
            }
        }
    }

    private void registercommands(){
        // commands from commands.guild
        commands.put("help", new Help());
        commands.put("info", new Help());
        commands.put("commands", new Help());
    }

    private String[] getargs(String raw){
        // remove bot_command_indicator from string
        raw = raw.replace(config.load("bot_command_indicator"), "").trim();
        // split string to args
        return raw.split(" ");
    }
}
