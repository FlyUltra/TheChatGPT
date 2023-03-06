package cz.flyultra.api;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;

public class ChatGPT {

    private JavaPlugin plugin;

    private OpenAiService service;

    private String idForAI;
    private String idForPlayer;

    private String model;

    private double temperature;
    private int maxTokens;
    private double topP;
    private double frequency;
    private double presence;

    /**
     *
     * HashMap for save conversations
     *
     */
    private HashMap<String, String> conversations;

    public ChatGPT(JavaPlugin plugin) {
        this.plugin = plugin;
        conversations = new HashMap<>();
    }

    /**
     *
     * Register method for specific parameters
     *
     */
    public void register(String idForAI, String idForPlayer, String model, double temperature, int maxTokens, double topP, double frequency, double presence) {
        this.idForAI = idForAI;
        this.idForPlayer = idForPlayer;
        this.model = model;
        this.temperature = temperature;
        this.maxTokens = maxTokens;
        this.topP = topP;
        this.frequency = frequency;
        this.presence = presence;
    }

    /**
     *
     * Login method for open connection
     *
     * @param token Dev API token from ChatGPT (OpenAI)
     */
    public void login(String token, int timeout) {
        service = new OpenAiService(token, timeout);
    }

    /**
     *
     * This create the space for answer by AI
     *
     */
    private void newLine(Player player, String message){
        String conversation = getConversation(player.getName());

        String context = conversation+"Human: " + message + "\nAI: ";
        conversations.replace("Fly", context);
    }

    /**
     *
     * This save the AI response
     *
     */
    private void AIResponse(Player player, String AI) {
        String conversation = getConversation(player.getName());
        String context = conversation+ AI +"\n";
        conversations.replace("Fly", context);
    }

    /**
     *
     * This method is the main part, here we can get answer from AI with conversation before
     *
     * @param player who asked
     * @param message question
     * @return answer from AI
     */
    public String getAnswer(Player player, String message) {
        if (!getConversations().containsKey(player.getName().toLowerCase())) {
            createConversation(player);
        }

        newLine(player, message);
        CompletionRequest AIRequest = CompletionRequest.builder()
                .prompt(getConversation(player.getName()))
                .model(model)
                .temperature(temperature)
                .maxTokens(maxTokens)
                .topP(topP)
                .frequencyPenalty(frequency)
                .presencePenalty(presence)
                .stop(Arrays.asList(idForPlayer+":", idForAI+":"))
                .build();

        String answerFromAI = service.createCompletion(AIRequest).getChoices().get(0).getText();
        AIResponse(player, answerFromAI);
        return answerFromAI;
    }

    /**
     *
     * This creates pattrn for conversation in hashmap
     *
     */
    public void createConversation(Player player) {
        String playerName = player.getName().toLowerCase();
        if (conversations.containsKey(playerName)) {
            return;
        }
        conversations.put(playerName, "");
    }

    /**
     *
     * refresh conversation
     *
     */
    public void reCreateConversation(Player player) {
        String playerName = player.getName().toLowerCase();
        if (conversations.containsKey(playerName)) {
            conversations.replace(playerName, "");
            return;
        }
        createConversation(player);
    }

    private HashMap<String, String> getConversations() {
        return conversations;
    }

    /**
     *
     * Get converastion by Player name
     *
     */
    public String getConversation(String name) {
        return getConversations().get(name.toLowerCase());
    }
}
