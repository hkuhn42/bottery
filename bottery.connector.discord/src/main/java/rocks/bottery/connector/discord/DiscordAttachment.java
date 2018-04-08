/**
 * 
 */
package rocks.bottery.connector.discord;

import java.net.MalformedURLException;
import java.net.URL;

import rocks.bottery.bot.AttachmentType;
import rocks.bottery.bot.IAttachment;
import sx.blah.discord.handle.obj.IMessage.Attachment;

/**
 * Attachment definition for discord
 * 
 * @author Harald Kuhn
 */
public class DiscordAttachment implements IAttachment {

	private static final long serialVersionUID = 1L;
	private Attachment		  attachment;

	public DiscordAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IAttachment#getType()
	 */
	@Override
	public AttachmentType getType() {
		return AttachmentType.FILE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IAttachment#getSize()
	 */
	@Override
	public long getSize() {
		return attachment.getFilesize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IAttachment#getName()
	 */
	@Override
	public String getName() {
		return attachment.getFilename();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IAttachment#getId()
	 */
	@Override
	public String getId() {
		return attachment.getStringID();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IAttachment#getUrl()
	 */
	@Override
	public URL getUrl() {
		try {
			return new URL(attachment.getUrl());
		}
		catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

}
