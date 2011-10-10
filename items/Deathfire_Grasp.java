package items;

import champions.Champion;
import util.Action;
import util.Effect;

public class Deathfire_Grasp extends Item {
	protected Deathfire_Grasp() {
		super("Deathfire Grasp",2610);
		
		Effect effect = new Effect("Deathfire Grasp") {
			public void once(Champion c) {
				c.ap += 60;
				c.manaregen += 2;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Deathfire Grasp Passive",0,false) {
			public void once(Champion c) {
				c.changeCDR(15);
				
				Effect eff = new Effect("Deathfire Active",0,false) {
					public void use(Champion champ, Champion target) {
						double percent = 30 + 3.5 * (champ.ap/100);
						double damage = target.hp*(percent / 100);
						if (damage < 200) damage = 200;
						target.takeDamage(Champion.MAGIC_DAMAGE,champ,damage);
					}
				};
				Action a = new Action(eff,500,60);
				c.addActive(a);
			}
		};
		this.uniquePassive = unique;
	}
}
