package items;

import champions.Champion;
import util.Effect;

public class Void_Staff extends Item {
	protected Void_Staff() {
		super("Void Staff",2295);
		
		Effect effect = new Effect("Void Staff") {
			public void once(Champion c) {
				c.ap += 70;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Void Staff Passive",0,false) {
			public void once(Champion c) {
				c.changeMagicPenPercent(40);
			}
		};
		this.uniquePassive = unique;
	}
}
