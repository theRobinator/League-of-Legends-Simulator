package items;

import champions.Champion;
import util.Effect;

public class Mercurys_Treads extends Item {
	protected Mercurys_Treads() {
		super("Mercury's Treads",1200);
		
		Effect effect = new Effect("Mercury's Treads") {
			public void once(Champion c) {
				c.mr += 25;
			}
		};
		this.effect = effect;
		
		Effect unique = new Effect("Mercury's Treads Passive",0,false) {
			public void once(Champion c) {
				c.movespeed += 70;
			}
			public double onDebuff(Champion c, double duration) {
				return duration * 0.65;
			}
		};
		this.uniquePassive = unique;
	}
}
