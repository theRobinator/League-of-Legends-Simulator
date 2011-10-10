package items;

import champions.Champion;
import util.Action;
import util.Effect;

public class Youmuus_Ghostblade extends Item {
	protected Youmuus_Ghostblade() {
		super("Youmuu's Ghostblade",2687);
		
		Effect effect = new Effect("Youmuu's Ghostblade") {
			public void once(Champion c) {
				c.damage += 30;
				c.changeCritChance(15);
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Youmuu's Ghostblade Passive",0,false) {
			public void once(Champion c) {
				c.armorpen += 20;
				c.changeCDR(15);
				
				Effect eff = new Effect("Youmuu's Active",0,false) {
					public void use(Champion owner, Champion enemy) {
						owner.addEffect(new Effect("Youmuu's Active",4,false) {
							int increases = 0;
							public void once(Champion champ) {
								champ.movespeed *= 1.2;
								champ.changeASPercent(50);
							}
							public void remove(Champion champ) {
								champ.movespeed *= 5/6.0;
								champ.changeASPercent(-50);
							}
							public void onAttack(Champion owner) {
								if (increases < 4) {
									updateTimer(-2);
								}
							}
						});
					}
				};
				
				Action active = new Action(eff,100000,60);
				c.addActive(active);
			}
		};
		this.uniquePassive = unique;
	}
}
