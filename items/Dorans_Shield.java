package items;

import champions.Champion;
import util.Effect;

public class Dorans_Shield extends Item {
	protected Dorans_Shield() {
		super("Doran's Shield",475);
		
		Effect effect = new Effect("Doran's Shield") {
			public void once(Champion c) {
				c.maxhp += 120;
				c.armor += 10;
				c.hpregen += 8/5.0;
			}
		};
		this.effect = effect;
	}
}
