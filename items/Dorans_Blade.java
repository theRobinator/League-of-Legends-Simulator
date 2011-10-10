package items;

import champions.Champion;
import util.Effect;

public class Dorans_Blade extends Item {
	protected Dorans_Blade() {
		super("Doran's Blade",475);
		
		Effect effect = new Effect("Doran's Blade") {
			public void once(Champion c) {
				c.maxhp += 100;
				c.damage += 10;
				c.lifesteal += 3;
			}
		};
		this.effect = effect;
	}
}
