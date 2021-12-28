import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
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
        out.println("Bot is ready.");
        jda.updateCommands().queue();
        jda.addEventListener(new Main());

        jda.upsertCommand("yuki", "Reply with Pong!").queue();
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        out.println("onSlashCommand hit.");
        out.println(event.getName());

        if (event.getName().equalsIgnoreCase("yuki")) {
            event.reply("Pong!").setEphemeral(false).queue();
        } else {
            event.reply("Invalid command.").setEphemeral(false).queue();
        }
    }
    @Override
    public void onReady(ReadyEvent event){
        out.println("Bot is final ready.");

    }
}


