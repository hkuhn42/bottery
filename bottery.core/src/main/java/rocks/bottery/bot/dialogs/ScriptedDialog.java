/**
 * 
 */
package rocks.bottery.bot.dialogs;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import rocks.bottery.bot.IActivity;
import rocks.bottery.bot.ISession;
import rocks.bottery.connector.GenericActivity;

/**
 * A dialog which delegates execution to a script engine
 * 
 * @author Harald Kuhn
 */
public class ScriptedDialog implements IDialog {

	private ScriptEngine engine;
	private String		 script;

	public ScriptedDialog(String script) {
		this("nashorn", script);
	}

	public ScriptedDialog(String engineName, String script) {
		this(getEngine(engineName), script);
	}

	public ScriptedDialog(ScriptEngine engine, String script) {
		this.engine = engine;
		this.script = script;
	}

	protected static ScriptEngine getEngine(String engineName) {
		return new ScriptEngineManager().getEngineByName(engineName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rocks.bottery.bot.IHandler#handle(rocks.bottery.bot.ISession, rocks.bottery.bot.IActivity)
	 */
	@Override
	public void handle(ISession session, IActivity activity) {
		try {

			Bindings bindings = new SimpleBindings();
			bindings.put("session", session);
			bindings.put("activity", activity);

			engine.eval(script, bindings);
		}
		catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		GenericActivity a = new GenericActivity();
		a.setText("Hello, Hak was here");
		new ScriptedDialog(
		        "function answer(session, activity) {print(activity.getText());var replay = session.getConnector().newReplyTo(activity);session.send(replay);}; answer(session, activity);")
		                .handle(null, a);
	}

}
