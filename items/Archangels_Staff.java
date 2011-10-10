package items;

import champions.Champion;
import util.Effect;

public class Archangels_Staff extends Item {
	protected Archangels_Staff() {
		super("Archangel's Staff",2855);
		
		Effect effect = new Effect("Archangel's Staff") {
			public void once(Champion c) {
				c.maxmana += 400;
				c.manaregen += 5;
				c.ap += 45;
			}
		};
		this.effect = effect;
		
		Effect passive = new Effect("Archangel's Passive") {
			public void once(Champion c) {
				double percent = 0.03;
				if (c.hasItem("Rabadon's Deathcap"))
					percent *= 1.3;
				c.ap += percent*c.maxmana;
			}
		};
		this.passive = passive;
		
		Effect unique = new Effect("Tear of the Goddess Passive",0,false) {
			public void once(Champion c) {
				c.maxmana += 1000;
			}
		};
		this.uniquePassive = unique;
	}
}
