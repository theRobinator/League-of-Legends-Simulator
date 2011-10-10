package items;

import champions.Champion;
import util.Effect;

public class Thornmail extends Item {
	protected Thornmail() {
		super("Thornmail",2000);
		
		Effect effect = new Effect("Thornmail") {
			public void once(Champion c) {
				c.armor += 100;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Thornmail Passive",0,false) {
			public double onDamage(Champion owner, Champion enemy, double damage, int type) {
				if (type == Champion.PHYSICAL_DAMAGE)
					enemy.takeDamage(Champion.MAGIC_DAMAGE, owner, damage*0.3);
				return damage;
			}
		};
		this.uniquePassive = unique;
	}
}
