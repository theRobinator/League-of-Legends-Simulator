package items;

import champions.Champion;
import util.Action;
import util.Effect;
import util.SlowEffect;

public class Hextech_Gunblade extends Item {
	protected Hextech_Gunblade() {
		super("Hextech Gunblade",3625);
		
		Effect effect = new Effect("Hextech Gunblade") {
			public void once(Champion c) {
				c.damage += 60;
				c.ap += 75;
				c.lifesteal += 20;
				c.spellvamp += 25;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Hextech Gunblade Passive",0,false) {
			public void once(Champion c) {
				Effect eff = new Effect("Gunblade Active",0,false) {
					public void use(Champion champ, Champion target) {
						target.takeDamage(Champion.MAGIC_DAMAGE, champ, 300);
						target.addEffect(new SlowEffect("Gunblade Slow",50,3,false));
					}
				};
				Action a = new Action(eff,700,60);
				c.addActive(a);
			}
		};
		this.uniquePassive = unique;
	}
}
