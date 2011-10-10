package items;

import champions.Champion;
import util.Effect;

public class Rod_of_Ages extends Item {
	protected Rod_of_Ages() {
		super("Rod of Ages",3035);
		
		Effect effect = new Effect("Rod of Ages") {
			public void once(Champion c) {
				c.maxhp += 630;
				c.maxmana += 725;
				c.ap += 80;
			}
		};
		this.effect = effect;
	}
}
