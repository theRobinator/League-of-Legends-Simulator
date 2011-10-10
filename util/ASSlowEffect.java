package util;

import champions.Champion;

public class ASSlowEffect extends Effect {
	int percent;
	public ASSlowEffect(String name, int slowpercent, double duration, boolean stackable) {
		super(name,duration,stackable);
		percent = slowpercent;
	}
	
	public void once(Champion champ) {
		champ.changeASPercent(-1*percent);
	}
	
	public void remove(Champion champ) {
		champ.changeASPercent(percent);
	}
}
