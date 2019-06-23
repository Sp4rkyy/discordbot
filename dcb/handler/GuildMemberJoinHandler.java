package dcb.handler;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;

import java.util.Random;

public class GuildMemberJoinHandler implements Runnable{

    private GuildMemberJoinEvent event;

    public GuildMemberJoinHandler(GuildMemberJoinEvent event){
        this.event = event;
    }

    public void run(){
        // try to open private channel.
        try{
            event.getUser().openPrivateChannel().queue((channel) ->
            {
                // prepare messages
                String[] messages = {
                        "Greetings "+event.getUser().getName()+"!"+"\n"+"Welcome to "+event.getGuild().getName()+"!"+"\n",
                        "Good to see you here "+event.getUser().getName()+"!"+"\n"
                };
                // send message
                channel.sendMessage(messages[new Random().nextInt(messages.length)]).queue();
            });
        }catch (Exception ignore){
            // no need to catch exceptions here but we do so that the thread will finish successful
        }
    }
}
