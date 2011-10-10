package items;

import champions.Champion;
import util.Effect;
import util.SheenEffect;
import util.SlowEffect;

public class Trinity_Force extends Item {
	static final Effect cooldown = new Effect("Sheen Cooldown",2,false);
	
	protected Trinity_Force() {
		super("Trinity Force",4070);
		
		Effect effect = new Effect("Trinity Force") {
			public void once(Champion c) {
				c.damage += 30;
				c.ap += 30;
				c.changeASPercent(30);
				c.changeCritChance(15);
				c.movespeed *= 1.12;
				c.maxhp += 250;
				c.maxmana += 250;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Trinity Force Passive",0,false) {
			public void onHit(Champion owner, Champion target, double damage, int type) {
				if (type == Champion.PHYSICAL_DAMAGE)
					target.addEffect(new SlowEffect("Trinity Force Slow",30,2.5,false));
			}
			public void onCast(Champion owner) {
				if (!owner.hasEffect("Sheen Increase") && !owner.hasEffect("Sheen Cooldown")) {
					owner.addEffect(new SheenEffect(owner.damage*1.5));
					owner.addEffect(cooldown);
				}
			}
		};
		this.uniquePassive = unique;
	}
}
