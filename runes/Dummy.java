package runes;

import util.Effect;

public class Dummy extends Rune {
	public Dummy() {
		super("Dummy Rune");
		this.effect = new Effect("Dummy Rune Effect",0,false);
	}
}
