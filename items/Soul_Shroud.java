package items;

import champions.Champion;
import util.Effect;

public class Soul_Shroud extends Item {
	protected Soul_Shroud() {
		super("Soul Shroud",2285);
		
		Effect effect = new Effect("Soul Shroud") {
			public void once(Champion c) {
				c.maxhp += 520;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Soul Shroud Passive",0,false) {
			public void once(Champion c) {
				c.manaregen += 12/5.0;
				c.changeCDR(10);
			}
		};
		this.uniquePassive = unique;
	}
}
