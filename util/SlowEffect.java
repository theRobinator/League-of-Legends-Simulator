package util;

import champions.Champion;

public class SlowEffect extends Effect {
	int percent;
	public SlowEffect(String name, int slowpercent, double duration, boolean stackable) {
		super(name,duration,stackable);
		percent = slowpercent;
		debuff = true;
	}
	
	public void once(Champion champ) {
		champ.movespeed *= (100-percent)/100;
	}
	
	public void remove(Champion champ) {
		champ.movespeed *= 100/(100-percent);
	}
}
