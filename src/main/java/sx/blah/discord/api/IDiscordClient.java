package sx.blah.discord.api;

import sx.blah.discord.handle.EventDispatcher;
import sx.blah.discord.handle.obj.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * Represents the main discord api
 */
public interface IDiscordClient {
	
	/**
	 * Gets the {@link EventDispatcher} instance for this client. Use this to handle events.
	 * 
	 * @return The event dispatcher instance.
	 */
	EventDispatcher getDispatcher();
	
	/**
	 * Gets the authorization token for this client.
	 * 
	 * @return The authorization token.
	 */
	String getToken();
	
	/**
	 * Logs the client in as the provided account.
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	void login() throws IOException, URISyntaxException;
	
	/**
	 * Sends a message to the desired channel.
	 * 
	 * @param content The content of the message.
	 * @param channelID The channel id of the channel to receive the message.
	 * @return The message object representing the sent message
	 * @throws IOException
	 */
	Message sendMessage(String content, String channelID) throws IOException;
	
	/**
	 * Edits a message. NOTE: Discord only supports editing YOUR OWN messages!
	 * 
	 * @param content The new content for the message to contain.
	 * @param messageID The message id of the message to edit.
	 * @param channelID The channel id of the channel the message belongs to.
	 * @return The new message.
	 */
	Message editMessage(String content, String messageID, String channelID);
	
	/**
	 * Deletes a message.
	 * 
	 * @param messageID The message id of the message to delete.
	 * @param channelID The channel id of the channel the message belongs to.
	 * @throws IOException
	 */
	void deleteMessage(String messageID, String channelID) throws IOException;
	
	/**
	 * FIXME: Fix this because it's fucking stupid.
	 * Allows you to change the info on your bot.
	 * Any fields you don't want to change should be left as an empty string ("") or null.
	 *
	 * @param username Username (if you want to change it).
	 * @param email Email (if you want to change it)
	 * @param password Password (if you want to change it).
	 */
	void changeAccountInfo(String username, String email, String password) throws UnsupportedEncodingException, URISyntaxException;
	
	/**
	 * Updates the bot's presence.
	 * 
	 * @param isIdle If true, the bot will be "idle", otherwise the bot will be "online".
	 * @param game The optional name of the game the bot is playing. If empty, the bot simply won't be playing a game.
	 */
	void updatePresence(boolean isIdle, Optional<String> game);
	
	/**
	 * Checks if the api is ready to be interacted with (if it is logged in).
	 * 
	 * @return True if ready, false if otherwise.
	 */
	boolean isReady();
	
	/**
	 * Gets the {@link User} this bot is representing.
	 * 
	 * @return The user object.
	 */
	User getOurUser();
	
	/**
	 * Gets a channel by its unique id.
	 * 
	 * @param channelID The id of the desired channel.
	 * @return The {@link Channel} object with the provided id.
	 */
	Channel getChannelByID(String channelID);
	
	/**
	 * Gets a guild by its unique id.
	 *
	 * @param guildID The id of the desired guild.
	 * @return The {@link Guild} object with the provided id.
	 */
	Guild getGuildByID(String guildID);
	
	/**
	 * Gets all the guilds the user the api represents is connected to.
	 * 
	 * @return The list of {@link Guild}s the api is connected to.
	 */
	List<Guild> getGuilds();
	
	/**
	 * Gets a user by its unique id.
	 *
	 * @param userID The id of the desired user.
	 * @return The {@link User} object with the provided id.
	 */
	@Deprecated //TODO: Maybe this was deprecated because a discriminator should be used?
	User getUserByID(String userID);
	
	/**
	 * Gets a {@link PrivateChannel} for the provided recipient.
	 * 
	 * @param user The user who will be the recipient of the private channel.
	 * @return The {@link PrivateChannel} object.
	 * 
	 * @throws Exception
	 */
	PrivateChannel getOrCreatePMChannel(User user) throws Exception;
	
	/**
	 * Toggles whether the bot is "typing".
	 * 
	 * @param channelId The channel to maintain the typing status to.
	 */
	void toggleTypingStatus(String channelId);
	
	/**
	 * Gets whether the bot is "typing".
	 * 
	 * @param channelId The channel to get the typing status for for this bot.
	 * @return True if the bot is typing, false if otherwise.
	 */
	boolean getTypingStatus(String channelId);
}