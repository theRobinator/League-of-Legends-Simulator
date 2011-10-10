package util;

import items.Item;
import runes.Rune;
import masteries.Mastery;

public class Build {
	public int wins = 0;
	public double avghp = 0;
	public double totalhp = 0;
	
	public Item[] items;
	public Mastery[] masteries;
	public Rune[] runes;
	
	public Build(Item[] items,Mastery[] masteries, Rune[] runes) {
		this.items = items;
		this.masteries = masteries;
		this.runes = runes;
	}
	
	public void addWin(double hpleft) {
		++wins;
		totalhp += hpleft;
		avghp = totalhp / wins;
	}
	
	public String toString() {
		if (items.length == 0 || items[0] == null) return "";
		String ws = wins+" wins with an average of "+avghp+" hp remaining with the following build:";
		
		String is = "Items: "+items[0].name;
		for (int i = 1; i < items.length; ++i)
			is += ", "+items[i].name;
		
		String ms = "Masteries: "+masteries[0].name;
		for (int i = 1; i < masteries.length; ++i)
			ms += ", "+masteries[i].name;
		
		String rs = "Runes: "+runes[0].name;
		for (int i = 1; i < runes.length; ++i)
			rs += ", "+runes[i].name;
		
		return ws+"\n"+is;
		//return ws+"\n"+is+"\n"+ms+"\n"+rs;
	}
}

