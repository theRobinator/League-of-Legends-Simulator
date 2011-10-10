package items;

import champions.Champion;
import util.Effect;

public class Nashors_Tooth extends Item {
	protected Nashors_Tooth() {
		super("Nashor's Tooth",2885);
		
		Effect effect = new Effect("Nashor's Tooth") {
			public void once(Champion c) {
				c.changeASPercent(50);
				c.ap += 55;
				c.manaregen += 2;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Nashor's Tooth Passive",0,false) {
			public void once(Champion c) {
				c.changeCDR(25);
			}
		};
		this.uniquePassive = unique;
	}
}
