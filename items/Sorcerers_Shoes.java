package items;

import champions.Champion;
import util.Effect;

public class Sorcerers_Shoes extends Item {
	protected Sorcerers_Shoes() {
		super("Sorcerer's Shoes",1100);
		
		Effect effect = new Effect("Sorcerer's Shoes") {
			public void once(Champion c) {
				c.magicpen += 20;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Sorcerer's Shoes Passive",0,false) {
			public void once(Champion c) {
				c.movespeed += 70;
			}
		};
		this.uniquePassive = unique;
	}
}
