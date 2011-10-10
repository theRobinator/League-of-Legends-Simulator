package items;

import champions.Champion;
import util.Effect;

public class Boots_of_Mobility extends Item {
	protected Boots_of_Mobility() {
		super("Boots of Mobility",1000);
		
		Effect unique = new Effect("Boots of Mobility Passive",0,false) {
			public void once(Champion c) {
				c.movespeed += 70;
			}
		};
		this.uniquePassive = unique;
	}
}
