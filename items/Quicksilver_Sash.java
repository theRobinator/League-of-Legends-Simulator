package items;

import champions.Champion;
import util.Action;
import util.Effect;

public class Quicksilver_Sash extends Item {
	protected Quicksilver_Sash() {
		super("Quicksilver Sash",1440);
		
		Effect effect = new Effect("Quicksilver Sash") {
			public void once(Champion c) {
				c.mr += 56;
				
				Effect eff = new Effect("Quicksilver Active",0,false) {
					public void use(Champion champ, Champion enemy) {
						champ.removeDebuffs();
					}
				};
				
				Action active = new Action(eff,100000,105);
				c.addActive(active);
			}
		};
		this.effect = effect;
	}
}
