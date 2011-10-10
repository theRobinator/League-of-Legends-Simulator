package items;

import champions.Champion;
import util.Effect;
import util.SheenEffect;

public class Lich_Bane extends Item {
	
	protected Lich_Bane() {
		super("Lich Bane",3470);
		
		Effect effect = new Effect("Lich Bane") {
			public void once(Champion c) {
				c.maxmana += 350;
				c.ap += 80;
				c.mr += 30;
				c.movespeed *= 1.07;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Lich Bane Passive",0,false) {
			public void onCast(Champion owner) {
				if (!owner.hasEffect("Sheen Increase") && !owner.hasEffect("Sheen Cooldown")) {
					owner.addEffect(new SheenEffect(owner.ap));
					owner.addEffect(new Effect("Sheen Cooldown",2,false));
				}
			}
		};
		this.uniquePassive = unique;
	}
}
