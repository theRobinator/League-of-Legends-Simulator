package items;

import champions.Champion;
import util.Effect;

public class Tiamat extends Item {
	protected Tiamat() {
		super("Tiamat",2070);
		
		Effect effect = new Effect("Tiamat") {
			public void once(Champion c) {
				c.damage += 50;
				c.hpregen += 3;
				c.manaregen += 1;
			}
		};
		this.effect = effect;
		
		// No passive because splash damage doesn't apply in 1v1
	}
}
