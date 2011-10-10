package items;

import champions.Champion;
import util.ASSlowEffect;
import util.Action;
import util.Effect;
import util.SlowEffect;

public class Randuins_Omen extends Item {
	protected Randuins_Omen() {
		super("Randuin's Omen",3075);
		
		Effect effect = new Effect("Randuin's Omen") {
			public void once(Champion c) {
				c.maxhp += 350;
				c.armor += 75;
				c.hpregen += 5;
				
				Effect eff = new Effect("Randuin's Active") {
					public void use(Champion owner, Champion target) {
						target.addEffect(new SlowEffect("Randuin's Slow",35,3,false));
						target.addEffect(new ASSlowEffect("Randuin's ASSlow",35,3,false));
					}
				};
				Action active = new Action(eff,800,60);
				c.addActive(active);
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Randuin's Omen Passive",0,false) {
			public void once(Champion c) {
				c.changeCDR(5);
			}
			public double onDamage(Champion owner, Champion enemy, double damage, int type) {
				if (Champion.rand.nextDouble() < 0.2) {
					enemy.addEffect(new SlowEffect("Randuin's Slow",35,3,false));
					enemy.addEffect(new ASSlowEffect("Randuin's ASSlow",35,3,false));
				}
				return damage;
			}
		};
		this.uniquePassive = unique;
	}
}
