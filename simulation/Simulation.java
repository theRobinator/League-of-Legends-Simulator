package simulation;

import static items.Items.AD_ITEMS;
import static items.Items.AP_ITEMS;
import static items.Items.TANK_ITEMS;
import static items.Items.TESTING_ITEMS;
import items.Item;
import java.util.LinkedList;
import masteries.Mastery;
import runes.Rune;
import champions.Champion;
import util.Build;

public class Simulation {
	public static final int NUM_THREADS = 6;
	
	public static final int PHYSICAL_DAMAGE = 0,
							ABILITY_POWER = 1,
							TANK = 2,
							ALL_DAMAGE = 3;
	
	static long battles = 0;
	static boolean verbose = true;
	
	public static SimThread[] threads = null;
	
	public static LinkedList<Build> simulate(int test, Champion champ, Rune[] givenrunes, Mastery[] givenmasteries,
											 Item[] givenitems, int maxgold, Champion[] opponents) {		
		int numperthread;
		switch (test) {
		case Simulation.PHYSICAL_DAMAGE : numperthread = AD_ITEMS.length;      break;
		case Simulation.ABILITY_POWER   : numperthread = AP_ITEMS.length;      break;
		case Simulation.TANK            : numperthread = TANK_ITEMS.length;    break;
		default				            : numperthread = TESTING_ITEMS.length; break;
		}
		
		threads = new SimThread[NUM_THREADS];
		
		int[] assigns = new int[NUM_THREADS];
		int base = numperthread / NUM_THREADS;
		for (int i = 0; i < NUM_THREADS; ++i)
			assigns[i] = base;
		int mod = numperthread % NUM_THREADS;
		for (int i = NUM_THREADS-1; mod != 0; --i) {
			++assigns[i];
			--mod;
		}
		System.out.println(AD_ITEMS.length+" items split into "+NUM_THREADS+" threads with thread assignments:");
		for (int i = 0; i < NUM_THREADS; ++i)
			System.out.print(assigns[i]+" ");
		System.out.println();
		int offset = 0;
		for (int i = 0; i < NUM_THREADS; ++i) {
			threads[i] = new SimThread(test,champ,givenrunes,givenmasteries,givenitems,maxgold,opponents,offset,offset+assigns[i]);
			threads[i].start();
			offset += assigns[i];
		}
		
		return null;
	}
}
