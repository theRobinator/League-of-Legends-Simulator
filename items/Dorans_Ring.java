package items;

import champions.Champion;
import util.Effect;

public class Dorans_Ring extends Item {
	protected Dorans_Ring() {
		super("Doran's Ring",475);
		
		Effect effect = new Effect("Doran's Ring") {
			public void once(Champion c) {
				c.maxhp += 100;
				c.manaregen += 1;
				c.ap += 15;
			}
		};
		this.effect = effect;
	}
}
