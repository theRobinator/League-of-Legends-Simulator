package items;

import champions.Champion;
import util.Effect;

public class Banshees_Veil extends Item {
	
	protected Banshees_Veil() {
		super("Banshee's Veil",2715);
		
		Effect effect = new Effect("Banshee's Veil") {
			public void once(Champion c) {
				c.hp += 375;
				c.mana += 375;
				c.mr += 50;
			}
		};
		this.effect = effect;
		
		final Effect unique = new Effect("Banshee's Veil Passive",0,false) {
			public double onDamage(Champion owner, Champion enemy, double damage, int type) {
				if (type == Champion.MAGIC_DAMAGE && !owner.hasEffect("Banshee's CD")) {
					owner.addEffect(new Effect("Banshee's CD",45,false));
					return -999999999;
				}else {
					return damage;
				}
			}
		};
		this.uniquePassive = unique;
	}
}
