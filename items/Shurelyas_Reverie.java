package items;

import champions.Champion;
import util.Action;
import util.Effect;
import util.SlowEffect;

public class Shurelyas_Reverie extends Item {
	protected Shurelyas_Reverie() {
		super("Shurelya's Reverie",2200);
		
		Effect effect = new Effect("Shurelya's Reverie") {
			public void once(Champion c) {
				c.maxhp += 330;
				c.hpregen += 6;
				c.manaregen += 3;
				
				
				Effect eff = new Effect("Shurelya's Active",0,false) {
					public void use(Champion owner, Champion target) {
						owner.addEffect(new SlowEffect("Shurelya's Speed",-40,3,false));
					}
				};
				
				Action active = new Action(eff,10000,60);
				c.addActive(active);
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Shurelya's Reverie Passive",0,false) {
			public void once(Champion c) {
				c.changeCDR(15);
			}
		};
		this.uniquePassive = unique;
	}
}
