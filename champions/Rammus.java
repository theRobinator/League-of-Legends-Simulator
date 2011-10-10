package champions;

import items.Item;
import items.Items;
import util.Action;
import util.ActionChoice;
import util.Effect;
import util.SlowEffect;

public class Rammus extends Champion {
	public Rammus() {
		super("Ramus");
		base_hp = 1968;
		base_maxhp = 1968;
		base_hpregen = 2.98;
		base_mana = 849;
		base_maxmana = 849;
		base_manaregen = 1.98;
		base_attackspeed = 0.86;
		attackspeedcalc = 0.65;
		base_damage = 105;
		base_ap = 0;
		base_armorpen = 0;
		base_armorpenpercent = 0;
		base_armorreduce = 0;
		base_magicpen = 0;
		base_magicpenpercent = 0;
		base_magicreduce = 0;
		base_critchance = 0;
		base_critdamage = 200;
		base_armor = 89.4;
		base_mr = 30;
		base_dodge = 0;
		base_lifesteal = 0;
		base_spellvamp = 0;
		base_manaburn = 0;
		base_cdr = 0;
		base_movespeed = 310;
		base_healmodifier = 1;
		base_range = 125;
		
		testingItemSet = new Item[]{	Items.MERCURYS_TREADS,Items.AEGIS_OF_THE_LEGION,Items.FORCE_OF_NATURE,
									Items.ATMAS_IMPALER,Items.BANSHEES_VEIL,Items.SUNFIRE_CAPE,
									Items.THORNMAIL,Items.WARMOGS_ARMOR,Items.NINJA_TABI,
									Items.SPIRIT_VISAGE,Items.GUARDIAN_ANGEL,Items.RANDUINS_OMEN,Items.FROZEN_HEART
		};
		
		Effect passive = new Effect("Passive") {
			public void once(Champion champ) {
				champ.damage += 0.25*champ.armor;
			}
		};
		effects.add(passive);
		
		Effect e1 = new Effect("Powerball") {
			public void use(Champion champ, Champion enemy) {
				enemy.takeDamage(MAGIC_DAMAGE, champ, 300+champ.ap);
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
