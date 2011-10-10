package items;

import champions.Champion;
import util.Action;
import util.Effect;

public class Executioners_Calling extends Item {
	protected Executioners_Calling() {
		super("Executioner's Calling",1600);
		
		Effect effect = new Effect("Executioner's Calling") {
			public void once(Champion c) {
				c.lifesteal += 18;
				c.changeCritChance(15);
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Executioner's Calling Passive",0,false) {
			public void onHit(final Champion owner, Champion target, double damage, int type) {
				target.addEffect(new Effect("Executioner's onHit",8,false) {
					public void selfPerSecond(Champion c) {
						c.takeDamage(Champion.TRUE_DAMAGE, owner, 4);
					}
				});
				
				Effect eff = new Effect("Executioner's Active",0,false) {
					public void use(Champion champ, Champion enemy) {
						enemy.addEffect(new Effect("Executioner's Active",8,false) {
							public void once(Champion champ) {
								champ.healmodifier *= 0.5;
							}
							public void remove(Champion champ) {
								champ.healmodifier *= 2;
							}
						});
					}
				};
				Action a = new Action(eff,700,20);
				owner.addActive(a);
			}
		};
		this.uniquePassive = unique;
	}
}
