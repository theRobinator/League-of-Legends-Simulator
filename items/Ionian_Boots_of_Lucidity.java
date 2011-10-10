package items;

import champions.Champion;
import util.Effect;

public class Ionian_Boots_of_Lucidity extends Item {
	protected Ionian_Boots_of_Lucidity() {
		super("Ionian Boots of Lucidity",1050);
		
		Effect unique = new Effect("Ionian Boots of Lucidity Passive",0,false) {
			public void once(Champion c) {
				c.changeCDR(15);
				c.movespeed += 70;
			}
		};
		this.uniquePassive = unique;
	}
}
