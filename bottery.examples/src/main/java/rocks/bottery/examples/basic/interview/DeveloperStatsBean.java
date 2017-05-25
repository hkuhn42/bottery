/**
 * 
 */
package rocks.bottery.examples.basic.interview;

import java.io.Serializable;

/**
 * @author Harald Kuhn
 *
 */
public class DeveloperStatsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String			  name;
	private Integer			  experience;
	private String			  mainLanguage;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public String getMainLanguage() {
		return mainLanguage;
	}

	public void setMainLanguage(String mainLanguage) {
		this.mainLanguage = mainLanguage;
	}

	@Override
	public String toString() {
		return super.toString() + ", " + name + " " + experience + " " + mainLanguage;
	}
}
