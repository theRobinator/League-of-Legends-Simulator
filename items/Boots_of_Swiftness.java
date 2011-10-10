package items;

import champions.Champion;
import util.Effect;

public class Boots_of_Swiftness extends Item {
	protected Boots_of_Swiftness() {
		super("Boots of Swiftness",1000);
		
		Effect unique = new Effect("Boots of Swiftness Passive",0,false) {
			public void once(Champion c) {
				c.movespeed += 100;
			}
		};
		this.uniquePassive = unique;
	}
}
