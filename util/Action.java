package util;

import champions.Champion;

public class Action {
	public double range;
	protected  Effect effect;
	protected double cooldown;
	protected int manacost = 0;
	public double casttime = 0.1;
	public double cdTimer;
	protected boolean available = true;
	
	public Action next = null;
	
	public Action(Effect effect, double range, double cooldown) {
		this.effect = effect;
		this.range = range;
		this.cooldown = cooldown;
		cdTimer = 0;
	}
	public Action(Effect effect, double range, double cooldown, int manacost) {
		this.effect = effect;
		this.range = range;
		this.cooldown = cooldown;
		this.manacost = manacost;
		cdTimer = 0;
	}
	public Action(Effect effect, double range, double cooldown, int manacost, double casttime) {
		this.effect = effect;
		this.range = range;
		this.cooldown = cooldown;
		this.manacost = manacost;
		cdTimer = 0;
		this.casttime = casttime;
	}
	
	public void use(Champion owner, Champion target, double timestamp) {
		//System.out.println(owner.name+" uses "+effect.name+" on "+target.name);
		owner.removeMana(manacost);
		if (effect.name.equals("physical attack"))
			cdTimer = 1/owner.attackspeed;
		else
			cdTimer = cooldown * (100-owner.cdr)/100;
		//System.out.println("CD Timer set to "+cdTimer);
		available = false;
		effect.use(owner, target);
	}
	
	public void updateCooldown(double elapsedTime) {
		cdTimer -= elapsedTime;
		if (cdTimer <= 0) {
			cdTimer = 0;
			available = true;
		}
		//System.out.println("CD Timer for "+effect.name+" set to "+cdTimer);

	}
	
	public boolean isAvailable(Champion owner) {
		return cdTimer <= 0 && owner.mana >= manacost;
	}
}
