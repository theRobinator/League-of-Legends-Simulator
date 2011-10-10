package items;

import champions.Champion;
import util.Effect;

public class The_Black_Cleaver extends Item {
	protected The_Black_Cleaver() {
		super("The Black Cleaver",2865);
		
		Effect effect = new Effect("The Black Cleaver") {
			public void once(Champion c) {
				c.damage += 55;
				c.changeASPercent(30);
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("The Black Cleaver Passive",0,false) {
			public void onHit(Champion owner, Champion target, double damage, int type) {
				target.addEffect(new Effect("Cleaver Reduction",5,true,3) {
					public void once(Champion owner) {
						owner.armor -= 15;
					}
					public void remove(Champion owner) {
						for (int i = 0; i < stacks; ++i)
							owner.armor += 15;
					}
				});
			}
		};
		this.uniquePassive = unique;
	}
}
