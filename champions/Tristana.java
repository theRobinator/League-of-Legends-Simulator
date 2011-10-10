package champions;

import util.Action;
import util.ActionChoice;
import util.Effect;
import util.SlowEffect;
import items.Item;
import items.Items;

public class Tristana extends Champion {
	
	public Tristana() {
		super("Tristana");
		base_hp = 1891;
		base_maxhp = 1891;
		base_hpregen = 3.36;
		base_mana = 769;
		base_maxmana = 769;
		base_manaregen = 1.69;
		base_attackspeed = 0.992;
		attackspeedcalc = 0.65;
		base_damage = 99;
		base_ap = 0;
		base_armorpen = 0;
		base_armorpenpercent = 0;
		base_armorreduce = 0;
		base_magicpen = 0;
		base_magicpenpercent = 0;
		base_magicreduce = 0;
		base_critchance = 0;
		base_critdamage = 200;
		base_armor = 69;
		base_mr = 30;
		base_dodge = 0;
		base_lifesteal = 0;
		base_spellvamp = 0;
		base_manaburn = 0;
		base_cdr = 0;
		base_movespeed = 310;
		base_healmodifier = 1;
		base_range = 712;
		
		testingItemSet = new Item[]{	Items.BERSERKERS_GREAVES,Items.INFINITY_EDGE,Items.FROZEN_MALLET,
									Items.YOUMUUS_GHOSTBLADE,Items.BANSHEES_VEIL,Items.MADREDS_BLOODRAZOR,
									Items.THE_BLOODTHIRSTER,Items.PHANTOM_DANCER,Items.STARKS_FERVOR,
									Items.THE_BLACK_CLEAVER,Items.TRINITY_FORCE
		};
		
		Effect e1 = new Effect("Rapid Fire") {
			public void use(Champion champ, Champion enemy) {
				champ.addEffect(new Effect("Rapid Fire",7,false) {
					public void once(Champion c) {
						c.changeASPercent(90);
					}
					public void remove(Champion c) {
						c.changeASPercent(-90);
					}
				});
			}
		};
		Action a1 = new Action(e1,1000000,20,80);
		actions[ActionChoice.SPELL1] = a1;
		
		Effect e2 = new Effect("Rocket Jump") {
			public void use(Champion champ, Champion enemy) {
				enemy.takeDamage(MAGIC_DAMAGE, champ, 250+0.8*champ.ap);
				enemy.addEffect(new SlowEffect("Rocket Jump Slow",60,2.5,false));
			}
		};
		Action a2 = new Action(e2, 900,22,80);
		actions[ActionChoice.SPELL2] = a2;
		
		Effect e3 = new Effect("Explosive Shot") {
			public void use(final Champion champ, Champion enemy) {
				final double dps = (25+champ.ap*0.25)/5;
				enemy.addEffect(new Effect("Explosive Shot",5,false) {
					public void once(Champion self) {
						self.healmodifier *= 0.5;
					}
					public void remove(Champion self) {
						self.healmodifier *= 2;
					}
					public void selfPerSecond(Champion self) {
						self.takeDamage(MAGIC_DAMAGE,champ,dps);
					}
				});
			}
		};
		Action a3 = new Action(e3, 1400,16,90);
		actions[ActionChoice.SPELL3] = a3;
		
		Effect e4 = new Effect("Buster Shot") {
			public void use(Champion champ, Champion enemy) {
				enemy.takeDamage(MAGIC_DAMAGE, champ, 500+1.5*champ.ap);
				champ.distance = 1000;
				enemy.distance = 1000;
			}
		};
		Action a4 = new Action(e4,1600,60,140);
		actions[ActionChoice.SPELL4] = a4;
	}
	
	public ActionChoice chooseAction(Champion enemy) {
		ActionChoice res = null;
		res = searchActives(enemy);
		if (res != null) return res;
		
		int tag = -1;
		if (canCast()) {
			if (actions[ActionChoice.SPELL4].isAvailable(this))
				tag = ActionChoice.SPELL4;
			else if (actions[ActionChoice.SPELL2].isAvailable(this))
				tag = ActionChoice.SPELL2;
			else if (actions[ActionChoice.SPELL3].isAvailable(this))
				tag = ActionChoice.SPELL3;
			else if (actions[ActionChoice.SPELL1].isAvailable(this))
				tag = ActionChoice.SPELL1;
		}
		if (tag == -1 && actions[ActionChoice.PHYSICAL_ATTACK].isAvailable(this))
			tag = ActionChoice.PHYSICAL_ATTACK;
		else if (tag == -1) {
			return nextAvailable(enemy);
		}
		
		return new ActionChoice(tag,enemy,actions[tag].casttime);
	}
}
