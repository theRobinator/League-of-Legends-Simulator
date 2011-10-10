package items;

import champions.Champion;
import util.Effect;
import util.SlowEffect;

public class Frozen_Mallet extends Item {
	protected Frozen_Mallet() {
		super("Frozen Mallet",3250);
		
		Effect effect = new Effect("Frozen Mallet") {
			public void once(Champion c) {
				c.maxhp += 700;
				c.damage += 20;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Frozen Mallet Passive",0,false) {
			public void onHit(Champion owner, Champion target, double damage, int type) {
				if (type == Champion.PHYSICAL_DAMAGE)
					target.addEffect(new SlowEffect("Frozen Mallet Slow",30,2.5,false));
			}
		};
		this.uniquePassive = unique;
	}
}
