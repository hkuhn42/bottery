/**
 * 
 */
package rocks.bottery.bot.recognizer.witai;

import java.util.Collections;
import java.util.List;

import rocks.bottery.bot.recognizers.CommandIntent;
import rocks.bottery.bot.recognizers.IEntity;

/**
 * @author Harald Kuhn
 *
 */
public class WitAiIntent extends CommandIntent {

	private static final long serialVersionUID = 1L;

	private Response			  intent;

	public WitAiIntent(Response intent) {
		super(intent.getText());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.recognizers.IIntent#getRecognizerIntent()
	 */
	@Override
	public Object getRecognizerIntent() {
		return intent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.recognizers.IIntent#getEntities()
	 */
	@Override
	public List<IEntity> getEntities() {
		// List<Entity> entities = intent.getEntities().getEntities();

		return Collections.emptyList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.recognizers.IIntent#getEntity(java.lang.String)
	 */
	@Override
	public IEntity getEntity(String name) {
		return null;
	}

}
