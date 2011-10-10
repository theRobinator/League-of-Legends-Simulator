package items;

import champions.Champion;
import util.Effect;

public class Haunting_Guise extends Item {
	protected Haunting_Guise() {
		super("Haunting Guise",1485);
		
		Effect effect = new Effect("Haunting Guise") {
			public void once(Champion c) {
				c.ap += 25;
				c.maxhp += 200;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Haunting Guise Passive",0,false) {
			public void once(Champion c) {
				c.magicpen += 20;
			}
		};
		this.uniquePassive = unique;
	}
}
