package items;

import champions.Champion;
import util.Effect;

public class Spirit_Visage extends Item {
	protected Spirit_Visage() {
		super("Spirit Visage",1550);
		
		Effect effect = new Effect("Spirit Visage") {
			public void once(Champion c) {
				c.mr += 30;
				c.maxhp += 250;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Spirit Visage Passive",0,false) {
			public void once(Champion c) {
				c.changeCDR(10);
				c.healmodifier *= 1.15;
			}
		};
		this.uniquePassive = unique;
	}
}
