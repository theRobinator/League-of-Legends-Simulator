package items;

import champions.Champion;
import util.Effect;

public class Wits_End extends Item {
	protected Wits_End() {
		super("Wit's End",2000);
		
		Effect effect = new Effect("Wit's End") {
			public void once(Champion c) {
				c.changeASPercent(40);
				c.mr += 30;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Wit's End Passive",0,false) {
			public void onHit(Champion owner, Champion target, double damage, int type) {
				target.takeDamage(Champion.MAGIC_DAMAGE, owner, 42);
				owner.addEffect(new Effect("Wit's End MR",5,true,4){
					public void once(Champion champ) {
						champ.mr += 5;
					}
					public void remove(Champion champ) {
						for (int i = 0; i < stacks; ++i)
							champ.mr -= 5;
					}
				});
			}
		};
		this.uniquePassive = unique;
	}
}
