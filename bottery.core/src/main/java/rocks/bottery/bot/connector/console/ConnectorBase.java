package rocks.bottery.bot.connector.console;

import java.util.Random;

import rocks.bottery.bot.ActivityType;
import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.IConnector;
import rocks.bottery.bot.IParticipant;
import rocks.bottery.bot.connector.GenericActivity;
import rocks.bottery.bot.connector.GenericConversation;
import rocks.bottery.bot.connector.GenericParticipant;

/**
 * 
 * @author Harald Kuhn
 *
 */
public abstract class ConnectorBase implements IConnector {

	@Override
	public IActivity newMessageTo(IParticipant recipient) {
		IActivity activity = new GenericActivity();
		activity.setType(ActivityType.MESSAGE);
		GenericConversation conversation = new GenericConversation();
		conversation.setId(String.valueOf(this.hashCode()));
		conversation.setChannel(getChannel());
		activity.setConversation(conversation);
		activity.setFrom(getConnectorAccount());

		activity.setId(String.valueOf("c" + new Random().nextLong()));

		activity.setRecipient(new GenericParticipant());
		activity.getRecipient().setId(recipient.getId());
		activity.getRecipient().setName(recipient.getName());
		return activity;
	}

	@Override
	public IActivity newReplyTo(IActivity toThisActivity) {
		IActivity reply = newMessageTo(toThisActivity.getFrom());
		reply.setConversation(toThisActivity.getConversation());
		return reply;
	}

	protected abstract String getChannel();

}