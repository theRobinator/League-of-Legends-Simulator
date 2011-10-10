package items;

import champions.Champion;
import util.Effect;

public class Ninja_Tabi extends Item {
	protected Ninja_Tabi() {
		super("Ninja Tabi",850);
		
		Effect effect = new Effect("Ninja Tabi") {
			public void once(Champion c) {
				c.armor += 25;
				c.dodge += 12;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Ninja Tabi Passive",0,false) {
			public void once(Champion c) {
				c.movespeed += 70;
			}
		};
		this.uniquePassive = unique;
	}
}
