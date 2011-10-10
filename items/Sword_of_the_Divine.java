package items;

import champions.Champion;
import util.Action;
import util.Effect;

public class Sword_of_the_Divine extends Item {
	protected Sword_of_the_Divine() {
		super("Sword of the Divine",1970);
		
		Effect effect = new Effect("Sword of the Divine") {
			public void once(Champion c) {
				c.changeASPercent(60);
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Sword of the Divine Passive",0,false) {
			int counter = 0;
			public void once(Champion owner) {
				Effect eff = new Effect("Divine Active",0,false) {
					public void once(Champion champ) {
						champ.addEffect(new Effect("Divine Active",8,false){
							public void once(Champion c) {
								c.armorpen += 30;
							}
							public void remove(Champion c) {
								c.armorpen -= 30;
							}
						});
					}
				};
				Action a = new Action(eff,1000000,40);
				owner.addActive(a);
			}
			public void onHit(Champion owner, Champion target, double damage, int type) {
				counter = (counter + 1) % 4;
				if (counter == 0) {
					target.takeDamage(Champion.MAGIC_DAMAGE, owner, 100);
				}
			}
		};
		this.uniquePassive = unique;
	}
}
