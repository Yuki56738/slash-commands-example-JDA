package Main;


import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

import static java.lang.System.out;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException, InterruptedException {
        Dotenv dotenv = Dotenv.load();
        JDA jda = JDABuilder.createDefault(dotenv.get("DISCORD_TOKEN"))
                .setActivity(Activity.playing("type /yuki"))
//                .setRawEventsEnabled(true)
                .addEventListeners(new Main())
                .build();
        jda.awaitReady();
//        jda.updateCommands().queue();
//        jda.addEventListener(new Main());

        jda.upsertCommand("yuki", "Reply with Pong!").queue();
        jda.upsertCommand("yuki-test", "yuki-test").queue();
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        out.println("onSlashCommand hit.");
        out.println(String.format("event name: %s", event.getName()));
        if (event.getUser().isBot()){
            return;
        }

        if (event.getName().equalsIgnoreCase("yuki")) {
            event.reply("Pong!").setEphemeral(false).queue();
        } else if (event.getName().equalsIgnoreCase("yuki-test")) {
            //event.reply("yuki-test").queue()
            MessageChannel channel = event.getChannel();
            AudioChannel voiceChannel = event.getMember().getVoiceState().getChannel();
            event.reply("yuki-test hit.").queue();

            channel.sendMessage(String.format("Channel: %s", channel.getName())).queue();
//            Channel voiceChannel = event.getVoiceChannel();
            out.println(String.format("voiceChannel: %s",voiceChannel.getName()));
            channel.sendMessage(String.format("VC: %s", voiceChannel.getName())).queue();


        }
    }

    @Override
    public void onReady(ReadyEvent event){
        out.println("Bot is ready.");

    }
}


