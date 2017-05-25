/**
 * 
 */
package rocks.bottery.bot.dialogs;

import rocks.bottery.bot.IActivity;

/**
 * @author Harald Kuhn
 *
 */
public class InvalidAnswerException extends Exception {

	private static final long serialVersionUID = 1L;

	private IActivity		  feedback;

	public InvalidAnswerException(IActivity feedback) {
		this.feedback = feedback;
	}

	public IActivity getFeedback() {
		return feedback;
	}

}
