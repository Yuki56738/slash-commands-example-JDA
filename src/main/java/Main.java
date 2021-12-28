import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

import static java.lang.System.out;

public class Main {
    public static void main(String[] args) throws LoginException, InterruptedException {
        Dotenv dotenv = Dotenv.load();
        JDA jda = JDABuilder.createDefault(dotenv.get("DISCORD_TOKEN"))
                .setActivity(Activity.playing("type /yuki"))
                .setRawEventsEnabled(true)
                .build();
        jda.awaitReady();
        out.println("Bot is ready.");

    }

}
