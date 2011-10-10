package items;

import champions.Champion;
import util.Effect;

public class Wriggles_Lantern extends Item {
	protected Wriggles_Lantern() {
		super("Wriggle's Lantern",1600);
		
		Effect effect = new Effect("Wriggle's Lantern") {
			public void once(Champion c) {
				c.damage += 23;
				c.armor += 30;
				c.lifesteal += 18;
			}
		};
		this.effect = effect;
	}
}
