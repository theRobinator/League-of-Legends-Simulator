package items;

import champions.Champion;
import util.Action;
import util.Effect;
import util.InvincibleEffect;

public class Zhonyas_Hourglass extends Item {
	protected Zhonyas_Hourglass() {
		super("Zhonya's Hourglass",3300);
		
		Effect effect = new Effect("Zhonya's Hourglass") {
			public void once(Champion c) {
				c.ap += 100;
				c.armor += 50;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Zhonya's Hourglass Passive",0,false) {
			public void once(Champion c) {
				Effect eff = new Effect("Zhonya's Active",0,false) {
					public void use(Champion champ, Champion enemy) {
						champ.addEffect(new InvincibleEffect("Zhonya's Invincibility",2,false));
					}
				};
				Action active = new Action(eff,1000000,90);
				c.addActive(active);
			}
		};
		this.uniquePassive = unique;
	}
}
