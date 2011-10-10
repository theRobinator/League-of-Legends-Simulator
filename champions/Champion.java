package champions;

import items.Item;
import runes.Rune;
import util.*;

import java.util.ArrayList;
import java.util.Random;
import masteries.Mastery;

public class Champion {
	public static final int PHYSICAL_DAMAGE = 0,
							MAGIC_DAMAGE = 1,
							TRUE_DAMAGE = 2;
	public static final Random rand = new Random(System.nanoTime());
	
	public final String name;
	
	/* *** CURRENT STATS *** */
	public double 
		hp,
		mana,
		attackspeed,
		armorpen,
		magicpen,
		critchance,
		cdr;
	public double
		maxhp,
		hpregen,
		maxmana,
		manaregen,
		attackspeedcalc, //The base lvl 1 value used for calculations
		damage,
		ap,
		armorpenpercent,
		armorreduce,
		magicpenpercent,
		magicreduce,
		critdamage,
		armor,
		mr,
		dodge,
		lifesteal,
		spellvamp,
		manaburn,
		movespeed,
		healmodifier,
		range;
		
	/* BASE STATS */
	protected double
		base_hp,
		base_maxhp,
		base_hpregen,
		base_mana,
		base_maxmana,
		base_manaregen,
		base_attackspeed,
		base_damage,
		base_ap,
		base_armorpen,
		base_armorpenpercent,
		base_armorreduce,
		base_magicpen,
		base_magicpenpercent,
		base_magicreduce,
		base_critchance,
		base_critdamage,
		base_armor,
		base_mr,
		base_dodge,
		base_lifesteal,
		base_spellvamp,
		base_manaburn,
		base_cdr,
		base_movespeed,
		base_healmodifier = 1,
		base_range;

	public boolean dead = false;
	public int distance;
	
	public Item[] testingItemSet, defaultBuild;
	
	protected final ArrayList<Effect> effects = new ArrayList<Effect>();
	protected Rune[] runes = new Rune[6];
	protected Mastery[] masteries = new Mastery[30];
	protected Item[] items = new Item[6];
	protected Action[] actions = new Action[13];
	
	public Champion(String name) {
		this.name = name;
		Effect eff = new Effect("physical attack",0){
			public void use(Champion owner, Champion target) {
				Effect old;
				if (target.takeDamage(PHYSICAL_DAMAGE, owner, owner.damage))
					for (int i = 0; i < owner.effects.size(); ++i) {
						old = owner.effects.get(i);
						old.onHit(owner, target, owner.damage, PHYSICAL_DAMAGE);
						i = effects.indexOf(old);
					}
			}
		};
		Action act = new Action(eff,range,1/attackspeed);
		actions[ActionChoice.PHYSICAL_ATTACK] = act;
	}

	/***** STATUS MODIFYING METHODS *****/
	public boolean takeDamage(int type, Champion from, double amount) {
		return takeDamage(type,from,amount,true);
	}
	public boolean takeDamage(int type, Champion from, double amount, boolean vamp) {
		Effect old;
		for (int i = 0; i < effects.size(); ++i) {
			old = effects.get(i);
			amount = old.onDamage(this, from, amount, type);
			i = effects.indexOf(old);
		}
		if (amount <= 0) return false;
		if (type == PHYSICAL_DAMAGE) {
			if (rand.nextDouble() < dodge / 100) return false;
			double armor = this.armor;
			armor -= from.armorreduce;
			if (armor >= 0) {
				armor -= from.armorpen;
				armor *= (100-from.armorpenpercent)/100;
				amount *= (100/(100 + armor));
			}else {
				amount *= 1 + Math.abs(armor)/(100-armor);
			}
			mana -= from.manaburn;
			if (mana < 0) mana = 0;
		}else if (type == MAGIC_DAMAGE) {
			double mr = this.mr;
			mr -= from.magicreduce;
			if (mr >= 0) {
				mr -= from.magicpen;
				mr *= (100-from.magicpenpercent)/100;
				amount *= (100/(100 + mr));
			}else {
				amount *= 1 + Math.abs(mr)/(100-mr);
			}
		}

		//Lifesteal and spell vamp implemented here
		if (vamp) {
			if (type == PHYSICAL_DAMAGE)
				from.heal((from.lifesteal/100)*amount);
			else
				from.heal((from.spellvamp/100)*amount);
		}
		
		hp -= amount;
		if (hp <= 0) {
			hp = 0;
			for (int i = 0; i < effects.size(); ++i) {
				old = effects.get(i);
				old.onDeath(this);
				i = effects.indexOf(old);
			}
			if (hp <= 0)
				dead = true;
		}
		return true;
	}
	
	public void heal(double amount) {
		hp += amount*healmodifier;
		if (hp > maxhp) hp = maxhp;
	}
	public void addMana(double amount) {
		mana += amount;
		if (mana > maxmana) mana = maxmana;
	}
	public void removeMana(double amount) {
		mana -= amount;
		if (mana < 0) mana = 0;
	}
	public void changeASPercent(double percent) {
		percent /= 100;
		attackspeed += percent*attackspeedcalc;
		if (attackspeed > 2.5) attackspeed = 2.5;
	}
	public void changeArmorPenPercent(double percent) {
		percent /= 100;
		armorpenpercent = 100 - (100-armorpenpercent)*(1-percent);
	}
	public void changeMagicPenPercent(double percent) {
		percent /= 100;
		magicpenpercent = 100 - (100-magicpenpercent)*(1-percent);
	}
	public void changeCritChance(double amount) {
		critchance += amount;
		if (critchance > 100) critchance = 100;
		else if (critchance < 0) critchance = 0;
	}
	public void changeCDR(double percent) {
		cdr += percent;
		if (cdr > 40) cdr = 40;
		else if (cdr < 0) cdr = 0;
	}

	
	
	
	/***** AI METHODS *****/
	public void timeUpdate(double elapsed, Champion enemy) {
		double healamount = elapsed * hpregen;
		double mregen = elapsed * manaregen;
		for (int i = 0; i < effects.size(); ++i) {
			Effect e = effects.get(i);
			healamount = e.onHeal(this, healamount);
			mregen = e.onManaRegen(this, mregen);
			for (int rep = 0; rep < (int)elapsed; ++rep) {
				e.selfPerSecond(this);
				e.enemyPerSecond(this, enemy);
			}
			e.updateTimer(elapsed);
			if (e.isExpired()) {
				e.remove(this);
				effects.remove(i);
				--i;
			}
		}
		for (Action a : actions) {
			if (a == null) continue;
			a.updateCooldown(elapsed);
		}
	}
	
	public void prepareSimulation(Champion enemy, int distance) {		
		for (int i = 0; i < effects.size(); ++i) {
			if (!effects.get(i).name.equals("Passive")) {
				effects.get(i).remove(this);
				effects.remove(i);
				--i;
			}
		}
		for (int i = ActionChoice.PHYSICAL_ATTACK; i <= ActionChoice.SPELL4; ++i)
			actions[i].cdTimer = 0;
		for (int i = ActionChoice.ITEM1; i <= ActionChoice.ITEM6; ++i)
			actions[i] = null;
		
		resetStats();
		this.distance = distance;
		
		for (Rune r : runes) {
			if (r == null) break;
			r.effect.once(this);
		}
		
		for (Mastery m : masteries) {
			if (m == null) break;
			m.effect.once(this);
		}
		
		for (Item i : items) {
			if (i.effect == null) continue;
			i.effect.once(this);
			i.effect.enemyOnce(this, enemy);
		}
		for (Item i : items) {
			if (i.uniquePassive == null) continue;
			if (!this.hasEffect(i.uniquePassive.name)) {
				addEffect(i.uniquePassive);
				i.uniquePassive.once(this);
			}
		}
		for (Item i : items) {
			if (i.passive == null) continue;
			addEffect(i.passive);
			i.passive.once(this);
		}
		for (Effect e : effects) {
			if (e.name.equals("passive")) {
				e.once(this);
				break;
			}
		}
		for (int i = 0; i < 6; ++i) {
			if (items[i] != null && items[i].active != null) actions[i+ActionChoice.ITEM1] = items[i].active;
		}
	
	}
	
	// This should be overridden by the implementing classes
	public ActionChoice chooseAction(Champion opponent) { return null; }
	
	public ActionChoice searchActives(Champion target) {
		for (int i = ActionChoice.ITEM1; i <= ActionChoice.ITEM6; ++i) {
			if (actions[i] != null) {
				return new ActionChoice(i,target,0.1);
			}
		}
		return null;
	}
	
	public void doAction(ActionChoice choice) {
		Effect old;
		if (choice.tag == ActionChoice.PHYSICAL_ATTACK)
			for (int i = 0; i < effects.size(); ++i) {
				old = effects.get(i);
				old.onAttack(this);
				i = effects.indexOf(old);
			}
		else if (choice.tag < ActionChoice.ITEM1)
			for (int i = 0; i < effects.size(); ++i) {
				old = effects.get(i);
				old.onCast(this);
				i = effects.indexOf(old);
			}
		actions[choice.tag].use(this, choice.target, choice.timestamp);
	}
	
	public boolean canCast() {
		int status = 1;
		for (Effect e : effects) {
			status *= e.canCast();
		}
		return status != 0;
	}
	
	public ActionChoice nextAvailable(Champion enemy) {
		int tag = 0;
		double min = Double.POSITIVE_INFINITY;
		for (int i = ActionChoice.PHYSICAL_ATTACK; i <= ActionChoice.SPELL4; ++i)
			if (actions[i].cdTimer < min) {
				min = actions[i].cdTimer;
				tag = i;
			}
		return new ActionChoice(tag,enemy,min);
	}
	
	public void setBuild(Build b) {
		int i;
		for (i = 0; i < b.masteries.length; ++i)
			masteries[i] = b.masteries[i];
		for (;i < masteries.length;++i)
			masteries[i] = null;
		for (i = 0; i < b.runes.length; ++i)
			runes[i] = b.runes[i];
		for (;i < runes.length;++i)
			runes[i] = null;
		for (i = 0; i < b.items.length; ++i)
			items[i] = b.items[i];
		for (;i < items.length;++i)
			items[i] = null;
	}
	
	public void setItems(Item[] newitems) {
		for (int i = 0; i < items.length; ++i)
			items[i] = newitems[i];
	}
	
	public void addEffect(Effect effect) {
		if (effect.debuff) {
			double c = 1;
			for (Effect e : effects)
				c = e.onDebuff(this,effect.duration);
			if (c <= 0) return;
			effect.duration = c;
		}
		for (Effect e : effects) {
			if (e.name.equals(effect.name)) {
				if (e.stackable && e.addStack()) {
					e.once(this);
				}
				return;
			}
		}
		effect.once(this);
		effects.add(effect);
	}
	public void removeEffect(Effect effect) {
		effect.remove(this);
		effects.remove(effect);
	}
	public void removeDebuffs() {
		for (int i = 0; i < effects.size(); ++i)
			if (effects.get(i).debuff) effects.remove(i); 
	}
	public boolean hasEffect(String effect) {
		for (Effect e : effects) {
			if (e.name.equals(effect)) return true;
		}
		return false;
	}
	public boolean hasItem(String item) {
		for (Item i : items) {
			if (i.name.equals(item)) return true;
		}
		return false;
	}
	public void addActive(Action a) {
		for (int i = ActionChoice.ITEM1; i <= ActionChoice.ITEM6; ++i)
			if (actions[i] != null) {
				actions[i] = a;
				break;
			}
	}
	
	protected void resetStats() {
		hp = base_hp;
		maxhp = base_maxhp;
		hpregen = base_hpregen;
		mana = base_mana;
		maxmana = base_maxmana;
		manaregen = base_manaregen;
		attackspeed = base_attackspeed;
		damage = base_damage;
		ap = base_ap;
		armorpen = base_armorpen;
		armorpenpercent = base_armorpenpercent;
		armorreduce = base_armorreduce;
		magicpen = base_magicpen;
		magicpenpercent = base_magicpenpercent;
		magicreduce = base_magicreduce;
		critchance = base_critchance;
		critdamage = base_critdamage;
		armor = base_armor;
		mr = base_mr;
		dodge = base_dodge;
		lifesteal = base_lifesteal;
		spellvamp = base_spellvamp;
		manaburn = base_manaburn;
		cdr = base_cdr;
		movespeed = base_movespeed;
		healmodifier = base_healmodifier;
		range = base_range;
		dead = false;
	}
	
	public void printItems() {
		for (Item i : items)
			System.out.print(i+" ");
		System.out.println();
	}
}
