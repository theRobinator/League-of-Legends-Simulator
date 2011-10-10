package items;

import champions.Champion;
import util.Effect;

public class Morellos_Evil_Tome extends Item {
	protected Morellos_Evil_Tome() {
		super("Morello's Evil Tome",2350);
		
		Effect effect = new Effect("Morello's Evil Tome") {
			public void once(Champion c) {
				c.ap += 75;
				c.manaregen += 12/5.0;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Morello's Evil Tome Passive",0,false) {
			public void once(Champion c) {
				c.changeCDR(20);
			}
		};
		this.uniquePassive = unique;
	}
}
