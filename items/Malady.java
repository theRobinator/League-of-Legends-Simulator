package items;

import champions.Champion;
import util.Effect;

public class Malady extends Item {
	protected Malady() {
		super("Malady",1825);
		
		Effect effect = new Effect("Malady") {
			public void once(Champion c) {
				c.ap += 25;
				c.changeASPercent(50);
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Malady Passive",0,false) {
			public void onHit(Champion owner, Champion target, double damage, int type) {
				target.takeDamage(Champion.MAGIC_DAMAGE, owner, 20);
				target.addEffect(new Effect("Malady Reduction",5,true,4) {
					public void once(Champion champ) {
						champ.mr -= 6;
					}
					public void remove(Champion champ) {
						for (int i = 0; i < stacks; ++i)
							champ.mr += 6;
					}
				});
			}
		};
		this.uniquePassive = unique;
	}
}
