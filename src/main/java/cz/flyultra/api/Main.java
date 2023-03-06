package cz.flyultra.api;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    private ChatGPT chatGPT;

    @Override
    public void onEnable() {
        instance = this;

        chatGPT = new ChatGPT(this);
        chatGPT.login("your token", 0);
        chatGPT.register("AI", "You", "text-davinci-003", 0.9D, 50, 1.0D, 0D, 0.6D);
    }

    @Override
    public void onDisable() {

    }

    public ChatGPT getChatGPT() {
        return chatGPT;
    }
}
