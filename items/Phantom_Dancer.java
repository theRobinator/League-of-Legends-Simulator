package items;

import champions.Champion;
import util.Effect;

public class Phantom_Dancer extends Item {
	protected Phantom_Dancer() {
		super("Phantom Dancer",2845);
		
		Effect effect = new Effect("Phantom Dancer") {
			public void once(Champion c) {
				c.changeASPercent(55);
				c.changeCritChance(30);
				c.movespeed *= 1.15;
			}
		};
		this.effect = effect;
	}
}
