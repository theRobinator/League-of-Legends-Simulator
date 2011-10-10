package items;

import champions.Champion;
import util.Effect;

public class Guinsoos_Rageblade extends Item {
	static final Effect asIncrease = new Effect("Guinsoo's Increase",5,true,8) {
		public void once(Champion owner) {
			owner.changeASPercent(4);
			owner.ap += 6;
		}
		public void remove(Champion owner) {
			for (int i = 0; i < stacks; ++i) {
				owner.changeASPercent(-4);
				owner.ap -= 6;
			}
		}
	};
	
	protected Guinsoos_Rageblade() {
		super("Guinsoo's Rageblade",2235);
		
		Effect effect = new Effect("Guinsoo's Rageblade") {
			public void once(Champion c) {
				c.damage += 35;
				c.ap += 45;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Guinsoo's Rageblade Passive",0,false) {
			public void onCast(Champion c) {
				c.addEffect(asIncrease);
			}
			public void onAttack(Champion c) {
				c.addEffect(asIncrease);
			}
		};
		this.uniquePassive = unique;
	}
}
