package util;

import champions.Champion;

public class Effect {
	public final String name;
	public final boolean stackable;
	private boolean expires = true;
	public int stacks = 1;
	public int maxstacks = 100000000;
	public double duration;
	double timer;
	public boolean debuff = false;
	
	public Effect(String name) {
		this.name = name;
		this.duration = 0;
		expires = false;
		stackable = true;
		timer = duration;
	}
	
	public Effect(String name, double duration) {
		this.name = name;
		this.duration = duration;
		if (duration == 0) expires = false;
		timer = duration;
		stackable = true;
	}
	public Effect(String name, double duration, boolean stackable) {
		this.name = name;
		this.duration = duration;
		if (duration == 0) expires = false;
		timer = duration;
		this.stackable = stackable;
	}
	public Effect(String name, double duration, boolean stackable, int maxstacks) {
		this.name = name;
		this.duration = duration;
		if (duration == 0) expires = false;
		timer = duration;
		this.stackable = stackable;
		this.maxstacks = maxstacks;
	}
	
	public void resetTimer() {
		timer = duration;
	}
	public void updateTimer(double elapsed) {
		if (expires) timer -= elapsed;
	}
	public boolean isExpired() {
		return expires && timer <= 0;
	}
	public boolean addStack() {
		if (stacks == maxstacks) return false;
		stacks++;
		timer = duration;
		return true;
	}
	
	
	// These effects trigger when the champion gets the effect
	public void once(Champion owner) { }
	public void enemyOnce(Champion owner, Champion eneym) { }

	// These effects trigger when the effect is actively used
	public void use(Champion owner, Champion target) { }

	// These effects activate every second and act on their owner
	public void selfPerSecond(Champion owner) { }

	// These effects activate every second and act on enemies
	public void enemyPerSecond(Champion owner, Champion target) { }

	// These effects return 1 if certain things are allowed
	public int canCast() { return 1; }
	public int canAttack() { return 1; }
	public int canMove() { return 1; }

	// These effects trigger when owner hits enemy for damage.
	public void onHit(Champion owner, Champion target, double damage, int type) { }

	// These effects trigger when the owner does an action
	public void onCast(Champion owner) { }
	public void onAttack(Champion owner) { }

	// These effects trigger when the owner takes damage of type from enemy
	// This returns the new amount of damage, or -1 for a miss
	public double onDamage(Champion owner, Champion enemy, double damage, int type) { return damage; }

	// These effects trigger when the champion heals. It returns a modified amount
	public double onHeal(Champion owner, double amount) { return amount; }
	public double onManaRegen(Champion owner, double amount) { return amount; }

	// These effects trigger when the champion dies
	public void onDeath(Champion owner) { }

	// These effects trigger on a debuff
	// They return a modified duration
	public double onDebuff(Champion owner, double duration) { return duration; }

	// These effects trigger when the effect is removed
	public void remove(Champion owner) { }
}
