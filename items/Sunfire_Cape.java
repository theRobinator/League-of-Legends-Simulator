package items;

import champions.Champion;
import util.Effect;

public class Sunfire_Cape extends Item {
	protected Sunfire_Cape() {
		super("Sunfire Cape",2610);
		
		Effect effect = new Effect("Sunfire Cape") {
			public void once(Champion c) {
				c.maxhp += 450;
				c.armor += 45;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Sunfire Cape Passive",0,false) {
			public void enemyPerSecond(Champion owner, Champion target) {
				target.takeDamage(Champion.MAGIC_DAMAGE, owner, 35);
			}
		};
		this.uniquePassive = unique;
	}
}
