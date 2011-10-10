package items;

import champions.Champion;
import util.Effect;

public class Abyssal_Scepter extends Item {
	protected Abyssal_Scepter() {
		super("Abyssal Scepter",2650);
		
		Effect effect = new Effect("Abyssal Scepter") {
			public void once(Champion c) {
				c.ap += 70;
				c.mr += 57;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Abyssal Scepter Passive",0,false) {
			public void once(Champion c) {
				c.magicreduce += 20;
			}
		};
		this.uniquePassive = unique;
	}
}
