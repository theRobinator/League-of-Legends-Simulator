package items;

import champions.Champion;
import util.Effect;

public class Will_of_the_Ancients extends Item {
	protected Will_of_the_Ancients() {
		super("Will of the Ancients",2100);
		
		Effect effect = new Effect("Will of the Ancients") {
			public void once(Champion c) {
				c.ap += 50;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Will of the Ancients Passive",0,false) {
			public void once(Champion c) {
				c.ap += 30;
				c.spellvamp += 25;
			}
		};
		this.uniquePassive = unique;
	}
}
