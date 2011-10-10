package items;

import champions.Champion;
import util.Effect;

public class Rabadons_Deathcap extends Item {
	protected Rabadons_Deathcap() {
		super("Rabadon's Deathcap",3600);
		
		Effect effect = new Effect("Rabadon's Deathcap") {
			public void once(Champion c) {
				c.ap += 155;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Rabadon's Deathcap Passive",0,false) {
			public void once(Champion c) {
				c.ap *= 1.3;
			}
		};
		this.uniquePassive = unique;
	}
}
