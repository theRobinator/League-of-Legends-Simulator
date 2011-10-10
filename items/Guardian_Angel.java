package items;

import champions.Champion;
import util.Effect;

public class Guardian_Angel extends Item {
	
	protected Guardian_Angel() {
		super("Guardian Angel",2600);
		
		Effect effect = new Effect("Guardian Angel") {
			public void once(Champion c) {
				c.armor += 68;
				c.mr += 38;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Guardian Angel Passive",0,false) {
			public void onDeath(Champion c) {
				if (!c.hasEffect("GA Cooldown")) {
					c.heal(750);
					c.mana += 375;
					c.addEffect(new Effect("GA Cooldown",300,false));
				}
			}
		};
		this.uniquePassive = unique;
	}
}
