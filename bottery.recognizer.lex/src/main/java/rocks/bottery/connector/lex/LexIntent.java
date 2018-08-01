/**
 * 
 */
package rocks.bottery.connector.lex;

import com.amazonaws.services.lexruntime.model.PostTextResult;

import rocks.bottery.bot.recognizers.CommandIntent;

/**
 * Intent which encapsulates the PostTextResult of a lex call
 * 
 * @author Harald Kuhn
 */
public class LexIntent extends CommandIntent {

	private static final long serialVersionUID = 1L;

	private PostTextResult	  result;

	public LexIntent(PostTextResult result) {
		super(result.getIntentName());
		this.result = result;
	}

	@Override
	public Object getRecognizerIntent() {
		return result;
	}

	@Override
	public boolean isFullfillment() {
		return result.getDialogState().equals("ReadyForFulfillment");
	}

	@Override
	public String getResponseSuggestion() {
		return result.getMessage();
	}
}
