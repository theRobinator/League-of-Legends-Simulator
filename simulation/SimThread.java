package simulation;

import static items.Items.AD_ITEMS;
import static items.Items.AP_ITEMS;
import static items.Items.TANK_ITEMS;
import static items.Items.TESTING_ITEMS;
import static runes.Runes.ALL_RUNES;

import java.util.Arrays;
import java.util.LinkedList;

import items.Item;
import masteries.Mastery;
import runes.Rune;
import util.ActionChoice;
import util.Build;
import champions.Champion;

public class SimThread extends Thread {
	int test;
	Champion champ;
	Rune[] givenrunes;
	Mastery[] givenmasteries;
	Item[] givenitems;
	int maxgold;
	Champion[] opponents;
	int itemoffset, offsetend;
	public LinkedList<Build> builds = new LinkedList<Build>();

	public boolean die = false;

	public SimThread(int test, Champion champ, Rune[] givenrunes, Mastery[] givenmasteries,
			 Item[] givenitems, int maxgold, Champion[] opponents, int itemoffset, int offsetend) {
		this.test = test;
		try {
			this.champ = champ.getClass().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.givenrunes = givenrunes;
		this.givenmasteries = givenmasteries;
		this.givenitems = givenitems;
		this.maxgold = maxgold;
		this.opponents = new Champion[opponents.length];
		try {
			for (int i = 0; i < opponents.length; ++i) {
				this.opponents[i] = opponents[i].getClass().newInstance();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		this.itemoffset = itemoffset;
		this.offsetend = offsetend;
	}

	public void run() {
		Item[] itemstotest;
		switch (test) {
		case Simulation.PHYSICAL_DAMAGE : itemstotest = AD_ITEMS;      break;
		case Simulation.ABILITY_POWER   : itemstotest = AP_ITEMS;      break;
		case Simulation.TANK            : itemstotest = TANK_ITEMS;    break;
		default				            : itemstotest = TESTING_ITEMS; break;
		}

		if (offsetend > itemstotest.length)
			offsetend = itemstotest.length;

		itemstotest = Arrays.copyOfRange(itemstotest, itemoffset, offsetend);

		/********* HANDLE GIVEN ARRAYS **********/
		Rune[] R0 = null, R1 = null, R2 = null, R3 = null, R4 = null, R5 = null;
		try {
			R0 = new Rune[]{givenrunes[0]};
			R1 = new Rune[]{givenrunes[1]};
			R2 = new Rune[]{givenrunes[2]};
			R3 = new Rune[]{givenrunes[3]};
			R4 = new Rune[]{givenrunes[4]};
			R5 = new Rune[]{givenrunes[5]};
		}catch (Exception e) {
			if (R0 == null) R0 = ALL_RUNES;
			if (R1 == null) R1 = ALL_RUNES;
			if (R2 == null) R2 = ALL_RUNES;
			if (R3 == null) R3 = ALL_RUNES;
			if (R4 == null) R4 = ALL_RUNES;
			if (R5 == null) R5 = ALL_RUNES;
		}
		
		Item[] I0 = null, I1 = null, I2 = null, I3 = null, I4 = null, I5 = null;
		try {
			I0 = new Item[]{givenitems[0]};
			I1 = new Item[]{givenitems[1]};
			I2 = new Item[]{givenitems[2]};
			I3 = new Item[]{givenitems[3]};
			I4 = new Item[]{givenitems[4]};
			I5 = new Item[]{givenitems[5]};
		}catch (Exception e) {
			if (I0 == null) I0 = itemstotest;
			if (I1 == null) I1 = itemstotest;
			if (I2 == null) I2 = itemstotest;
			if (I3 == null) I3 = itemstotest;
			if (I4 == null) I4 = itemstotest;
			if (I5 == null) I5 = itemstotest;
		}
		
		
		ActionChoice mychoice, enemychoice;
		double timestamp, elapsed = 0.0;
		int goldtotal, enemygold, distance;
		Build currbuild;
		
		/*** MY MASTERIES ***/
		// This is currently not implemented, masteries should be set in the calling method
		
		/*** MY RUNES ***/
		for (int r0 = 0 ; r0 < R0.length; ++r0) {
		for (int r1 = r0; r1 < R1.length; ++r1) {
		for (int r2 = r1; r2 < R2.length; ++r2) {
		for (int r3 = r2; r3 < R3.length; ++r3) {
		for (int r4 = r3; r4 < R4.length; ++r4) {
		for (int r5 = r4; r5 < R5.length; ++r5) {

			/*** MY ITEMS ***/
			goldtotal = maxgold;
			for (int i0 = 0; i0 < I0.length; ++i0) {
			if (goldtotal < I0[i0].cost) continue;
			goldtotal -= I0[i0].cost;
			for (int i1 = i0; i1 < I1.length; ++i1) {
			if (goldtotal < I1[i1].cost) continue;
			goldtotal -= I1[i1].cost;
			for (int i2 = i1; i2 < I2.length; ++i2) {
			if (goldtotal < I2[i2].cost) continue;
			goldtotal -= I2[i2].cost;
			for (int i3 = i2; i3 < I3.length; ++i3) {
			if (goldtotal < I3[i3].cost) continue;
			goldtotal -= I3[i3].cost;
			for (int i4 = i3; i4 < I4.length; ++i4) {
			if (goldtotal < I4[i4].cost) continue;
			goldtotal -= I4[i4].cost;
			for (int i5 = i4; i5 < I5.length; ++i5) {
			if (goldtotal < I5[i5].cost) continue;
			goldtotal -= I5[i5].cost;
			 
			    currbuild = new Build(new Item[]{I0[i0],I1[i1],I2[i2],I3[i3],I4[i4],I5[i5]},
			                          givenmasteries,
			                          new Rune[]{R0[r0],R1[r1],R2[r2],R3[r3],R4[r4],R5[r5]}
			                         );
			    //System.out.println("Testing build "+currbuild);
			    champ.setBuild(currbuild);
			    builds.add(currbuild);
			 
			    /***** ENEMY CHAMPIONS *****/
			    for (Champion enemy : opponents) {
			 
			        
			    	enemygold = maxgold;
			    	Item[] Eitems = enemy.testingItemSet;
			    	for (int e0 = 0; e0 < Eitems.length; ++e0) {
					if (enemygold < Eitems[e0].cost) continue;
					enemygold -= Eitems[e0].cost;
					for (int e1 = e0; e1 < Eitems.length; ++e1) {
					if (enemygold < Eitems[e1].cost) continue;
					enemygold -= Eitems[e1].cost;
					for (int e2 = e1; e2 < Eitems.length; ++e2) {
					if (enemygold < Eitems[e2].cost) continue;
					enemygold -= Eitems[e2].cost;
					for (int e3 = e2; e3 < Eitems.length; ++e3) {
					if (enemygold < Eitems[e3].cost) continue;
					enemygold -= Eitems[e3].cost;
					for (int e4 = e3; e4 < Eitems.length; ++e4) {
					if (enemygold < Eitems[e4].cost) continue;
					enemygold -= Eitems[e4].cost;
					for (int e5 = e4; e5 < Eitems.length; ++e5) {
					if (enemygold < Eitems[e5].cost) continue;
					enemygold -= Eitems[e5].cost;
			 
						enemy.setItems(new Item[]{Eitems[e0],Eitems[e1],Eitems[e2],Eitems[e3],Eitems[e4],Eitems[e5]});

				        timestamp = 0.0;
				        elapsed = 0.0;
				        distance = (int)Math.max(champ.range, enemy.range);
				        champ.prepareSimulation(enemy,distance);
				        enemy.prepareSimulation(champ,distance);
				        //System.out.print(champ.name+" with ");
				        //champ.printItems();
				        //System.out.print(enemy.name+" with ");
				        //enemy.printItems();
				        while (!champ.dead && !enemy.dead) {
				            champ.timeUpdate(elapsed,enemy);
				            enemy.timeUpdate(elapsed,champ);
							 
				            if (champ.dead || enemy.dead) break;
				            
				            timestamp += elapsed;
				        	
				            mychoice = champ.chooseAction(enemy);
				            enemychoice = enemy.chooseAction(champ);
				            //System.out.println("Time = "+timestamp);
				            if (mychoice.timestamp <= enemychoice.timestamp) {
				                champ.doAction(mychoice);
				                elapsed = mychoice.timestamp;
				            }
				            if (enemychoice.timestamp <= mychoice.timestamp) {
				                enemy.doAction(enemychoice);
				                elapsed = enemychoice.timestamp;
				            }
				            if (die)
				            	return;
				        }
				        if (enemy.dead) {
				        	//System.out.println(champ.name+" wins!");
				        	currbuild.addWin(champ.hp);
				        }
				        ++Simulation.battles;
				        if (Simulation.battles % 10000 == 0)
				        	System.out.print("\r"+Simulation.battles+" fought so far...");

				    enemygold += Eitems[e5].cost;
					}
					enemygold += Eitems[e4].cost;
					}
					enemygold += Eitems[e3].cost;
					}
					enemygold += Eitems[e2].cost;
					}
					enemygold += Eitems[e1].cost;
					}
					enemygold += Eitems[e0].cost;
					} 
			    }
			 
			goldtotal += I5[i5].cost;
			}
			goldtotal += I4[i4].cost;
			}
			goldtotal += I3[i3].cost;
			}
			goldtotal += I2[i2].cost;
			}
			goldtotal += I1[i1].cost;
			}
			goldtotal += I0[i0].cost;
			}			
		}
		}	
		}
		}
		}
		}
	}
}
