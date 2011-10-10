package items;

import champions.Champion;
import util.Effect;

public class Chalice_of_Harmony extends Item {
	protected Chalice_of_Harmony() {
		super("Chalice of Harmony",890);
		
		Effect effect = new Effect("Chalice of Harmony") {
			public void once(Champion c) {
				c.mr += 30;
				c.manaregen += 7.5/5;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Chalice of Harmony Passive",0,false) {
			public double onManaRegen(Champion owner, double amount) {
				double percentmissing = (owner.maxmana-owner.mana)/owner.maxmana;
				amount *= 1 + percentmissing;
				return amount;
			}
		};
		this.uniquePassive = unique;
	}
}
