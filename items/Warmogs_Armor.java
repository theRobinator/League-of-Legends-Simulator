package items;

import champions.Champion;
import util.Effect;

public class Warmogs_Armor extends Item {
	protected Warmogs_Armor() {
		super("Warmog's Armor",3000);
		
		Effect effect = new Effect("Warmog's Armor") {
			public void once(Champion c) {
				c.maxhp += 1370;
				c.hpregen += 9;
			}
		};
		this.effect = effect;
	}
}
