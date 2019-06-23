package dcb.commands.guild;

import dcb.util.Config;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Help implements GuildCommand {
    @Override
    public boolean permission(Member member) {
        return member.hasPermission(Permission.MESSAGE_WRITE);
    }

    @Override
    public void execute(GuildMessageReceivedEvent event, Member member, String[] args) {
        //help
        if(args[0].toLowerCase().equals("help")){
            String msg = "info - Provides information about me\n"+
                    "commands - Shows a list of known commands ( Modules not included )\n";
            event.getChannel().sendMessage(msg).queue();
        }
        //info
        if(args[0].toLowerCase().equals("info")){
            String msg =    "------[ Info ]-----\n" +
                    "Ping: "+event.getJDA().getGatewayPing()+"\n"+
                    "Used by "+event.getJDA().getGuilds().size()+" guilds\n"+
            event.getChannel().sendMessage(msg).queue();
        }
        //commands
        if(args[0].toLowerCase().equals("commands")) {
            event.getChannel().sendMessage(" ").queue();
        }
    }
}
