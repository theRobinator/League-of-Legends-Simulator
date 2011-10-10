package items;

import champions.Champion;
import util.Effect;

public class Madreds_Bloodrazor extends Item {
	protected Madreds_Bloodrazor() {
		super("Madred's Bloodrazor",3800);
		
		Effect effect = new Effect("Madred's Bloodrazor") {
			public void once(Champion c) {
				c.damage += 30;
				c.changeASPercent(40);
				c.armor += 25;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Madred's Bloodrazor Passive",0,false) {
			public void onHit(Champion owner, Champion target, double damage, int type) {
				if (type == Champion.PHYSICAL_DAMAGE)
					target.takeDamage(Champion.MAGIC_DAMAGE, owner, target.maxhp*0.04,false);
			}
		};
		this.uniquePassive = unique;
	}
}
