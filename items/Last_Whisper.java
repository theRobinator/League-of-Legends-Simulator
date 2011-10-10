package items;

import champions.Champion;
import util.Effect;

public class Last_Whisper extends Item {
	protected Last_Whisper() {
		super("Last Whisper",2290);
		
		Effect effect = new Effect("Last Whisper") {
			public void once(Champion c) {
				c.damage += 40;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Last Whisper Passive",0,false) {
			public void once(Champion c) {
				c.changeArmorPenPercent(40);
			}
		};
		this.uniquePassive = unique;
	}
}
