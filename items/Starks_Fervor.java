package items;

import champions.Champion;
import util.Effect;

public class Starks_Fervor extends Item {
	protected Starks_Fervor() {
		super("Stark's Fervor",2550);
		
		Effect effect = new Effect("Stark's Fervor") {
			public void once(Champion c) {
				c.changeASPercent(20);
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Stark's Fervor Passive",0,false) {
			public void once(Champion c) {
				c.lifesteal += 20;
				c.changeASPercent(20);
				c.hpregen += 6;
				c.armorreduce = 20;
			}
		};
		this.uniquePassive = unique;
	}
}
