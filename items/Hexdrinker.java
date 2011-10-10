package items;

import champions.Champion;
import util.Effect;
import util.ShieldEffect;

public class Hexdrinker extends Item {
	
	protected Hexdrinker() {
		super("Hexdrinker",1800);
		
		Effect effect = new Effect("Hexdrinker") {
			public void once(Champion c) {
				c.damage += 35;
				c.mr += 30;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Hexdrinker Passive",0,false) {
			public double onDamage(Champion owner, Champion enemy, double damage, int type) {
				if (type == Champion.MAGIC_DAMAGE && owner.hp - damage < owner.maxhp*0.3 && !owner.hasEffect("Hexdrinker CD")) {
					owner.addEffect(new ShieldEffect("Hexdrinker Shield",Champion.MAGIC_DAMAGE,300,4,false));
					owner.addEffect(new Effect("Hexdrinker CD",60,false));
				}
				return damage;
			}
		};
		this.uniquePassive = unique;
	}
}
