package simulation;

import champions.*;
import items.Item;
import items.Items;
import masteries.Mastery;
import masteries.Masteries;
import runes.Rune;
import runes.Runes;

public class LolSim {
	public static final int N = 20;
	
	public static void main(String[] args) {
		Champion champ = new Tristana();
		
		Item[] currbuild = {Items.BERSERKERS_GREAVES};
		Rune[] currrunes = {Runes.DUMMY_RUNE};
		Mastery[] currmasteries = {Masteries.DUMMY};
		
		int test_type = Simulation.PHYSICAL_DAMAGE;
		
		int goldlimit = 500000;
		
		Champion[] enemies = {new Tristana()};
		
		Runtime.getRuntime().addShutdownHook(new ResultsPrinter());
		
		Simulation.simulate(test_type, champ, currrunes, currmasteries, currbuild, goldlimit, enemies);
	}
}
