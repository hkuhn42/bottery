/**
 * 
 */
package rocks.bottery.connector.discord;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import rocks.bottery.bot.ActivityType;
import rocks.bottery.bot.Choice;
import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IAttachment;
import rocks.bottery.bot.IConversation;
import rocks.bottery.bot.IIntent;
import rocks.bottery.bot.IParticipant;
import sx.blah.discord.handle.obj.IMessage;

/**
 * @author Harald Kuhn
 *
 */
public class DiscordActivity implements IActivity {

	private IMessage message;

	public DiscordActivity(IMessage message) {
		this.message = message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getId()
	 */
	@Override
	public String getId() {
		return message.getStringID();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#setId(java.lang.String)
	 */
	@Override
	public void setId(String id) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getTopic()
	 */
	@Override
	public String getTopic() {
		return message.getContent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#setTopic(java.lang.String)
	 */
	@Override
	public void setTopic(String topic) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getType()
	 */
	@Override
	public ActivityType getType() {
		ActivityType type = null;
		// TODO map types
		switch (message.getType()) {
			case DEFAULT:
				type = ActivityType.MESSAGE;
				break;
			default:
				type = ActivityType.OTHER;
				break;
		}
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#setType(rocks.bottery.bot.ActivityType)
	 */
	@Override
	public void setType(ActivityType type) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getFrom()
	 */
	@Override
	public IParticipant getFrom() {
		return new DiscordParticipant(message.getAuthor());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#setFrom(rocks.bottery.bot.IParticipant)
	 */
	@Override
	public void setFrom(IParticipant from) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getRecipient()
	 */
	@Override
	public IParticipant getRecipient() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#setRecipient(rocks.bottery.bot.IParticipant)
	 */
	@Override
	public void setRecipient(IParticipant recipient) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getText()
	 */
	@Override
	public String getText() {
		return message.getContent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#setText(java.lang.String)
	 */
	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getConversation()
	 */
	@Override
	public IConversation getConversation() {
		return new DiscordConversation(message.getChannel(), "discord");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#setConversation(rocks.bottery.bot.IConversation)
	 */
	@Override
	public void setConversation(IConversation conversation) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#setAttachments(java.util.List)
	 */
	@Override
	public void setAttachments(List<IAttachment> attachement) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getAttachments()
	 */
	@Override
	public List<IAttachment> getAttachments() {
		if (message.getAttachments() != null && message.getAttachments().size() > 0) {
			return message.getAttachments().stream().map(obj -> new DiscordAttachment(obj)).collect(Collectors.toList());
		}
		return Collections.emptyList(); // message.getAttachments();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getConnectorActivity()
	 */
	@Override
	public Object getConnectorActivity() {
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getIntent()
	 */
	@Override
	public IIntent<?> getIntent() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#setIntent(rocks.bottery.bot.IIntent)
	 */
	@Override
	public void setIntent(IIntent<?> intent) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#setChoices(java.util.List)
	 */
	@Override
	public void setChoices(List<Choice<?>> choices) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IActivity#getChoices()
	 */
	@Override
	public List<Choice<?>> getChoices() {
		return null;
	}

}
