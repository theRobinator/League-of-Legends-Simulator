package items;

import champions.Champion;
import util.Effect;

public class The_Bloodthirster extends Item {
	protected The_Bloodthirster() {
		super("The Bloodthirster",3000);
		
		Effect effect = new Effect("The Bloodthirster") {
			public void once(Champion c) {
				c.damage += 100;
				c.lifesteal += 25;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("The Bloodthirster Passive",0,false) {
			public void once(Champion c) {
				
			}
		};
		this.uniquePassive = unique;
	}
}
