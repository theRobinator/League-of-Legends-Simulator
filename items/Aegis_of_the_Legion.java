package items;

import champions.Champion;
import util.Effect;

public class Aegis_of_the_Legion extends Item {
	protected Aegis_of_the_Legion() {
		super("Aegis of the Legion",1925);
		
		Effect effect = new Effect("Aegis of the Legion") {
			public void once(Champion c) {
				c.maxhp += 270;
				c.armor += 18;
				c.mr += 24;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Aegis Passive",0,false) {
			public void once(Champion c) {
				c.armor += 12;
				c.mr += 15;
				c.damage += 8;
			}
		};
		this.uniquePassive = unique;
	}
}
