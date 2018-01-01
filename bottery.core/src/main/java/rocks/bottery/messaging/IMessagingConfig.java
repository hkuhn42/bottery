package rocks.bottery.messaging;

import rocks.bottery.bot.ICrypt;

public interface IMessagingConfig {

	/**
	 * get a string setting
	 * 
	 * @param name
	 * @return
	 */
	public String getSetting(String name);

	ICrypt getCrypt();

	void setCrypt(ICrypt crypt);
}
