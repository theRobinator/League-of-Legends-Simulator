package items;

import champions.Champion;
import util.Effect;

public class Berserkers_Greaves extends Item {
	protected Berserkers_Greaves() {
		super("Berserker's Greaves",920);
		
		Effect effect = new Effect("Berserker's Greaves") {
			public void once(Champion c) {
				c.changeASPercent(25);
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Berserker's Greaves Passive",0,false) {
			public void once(Champion c) {
				c.movespeed += 70;
			}
		};
		this.uniquePassive = unique;
	}
}
