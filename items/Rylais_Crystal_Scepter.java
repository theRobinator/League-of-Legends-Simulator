package items;

import champions.Champion;
import util.Effect;
import util.SlowEffect;

public class Rylais_Crystal_Scepter extends Item {
	protected Rylais_Crystal_Scepter() {
		super("Rylai's Crystal Scepter",3105);
		
		Effect effect = new Effect("Rylai's Crystal Scepter") {
			public void once(Champion c) {
				c.maxhp += 500;
				c.ap += 80;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Rylai's Crystal Scepter Passive",0,false) {
			public void onHit(Champion owner, Champion target, double damage, int type) {
				if (type == Champion.MAGIC_DAMAGE) {
					target.addEffect(new SlowEffect("Rylai's Slow",35,1.5,false));
				}
			}
		};
		this.uniquePassive = unique;
	}
}
