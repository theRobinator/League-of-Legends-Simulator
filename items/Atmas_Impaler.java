package items;

import champions.Champion;
import util.Effect;

public class Atmas_Impaler extends Item {
	protected Atmas_Impaler() {
		super("Atma's Impaler",2355);
		
		Effect effect = new Effect("Atma's Impaler") {
			public void once(Champion c) {
				c.armor += 45;
				c.changeCritChance(18);
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Atma's Passive",0,false) {
			public void once(Champion c) {
				c.damage += 0.02*c.maxhp;
			}
		};
		this.uniquePassive = unique;
	}
}
