package simulation;

import java.util.Collections;
import java.util.LinkedList;

import util.Build;
import util.BuildComparator;

public class ResultsPrinter extends Thread {
	static final int N = 20;
	
	public void run() {
		Simulation.verbose = false;
		
		//Kill all the threads first
		for (int i = 0; i < Simulation.threads.length; ++i) {
			Simulation.threads[i].die = true;
		}
		int count = 0;
		// Wait for the threads to quit
		while (true) {
			for (int i = 0; i < Simulation.threads.length; ++i)
				if (!Simulation.threads[i].isAlive()) ++count;
			if (count == Simulation.threads.length) break;
			count = 0;
		}
		
		LinkedList<Build> builds = new LinkedList<Build>();
		for (int i = 0; i < Simulation.threads.length; ++i) {
			for (Build b : Simulation.threads[i].builds)
				builds.add(b);
		}
		
		Collections.sort(builds,new BuildComparator());
		System.out.println("\n\n"+Simulation.battles+" fought with the following "+N+" builds being the best:\n\n");
		for (int i = 0; i < N; ++i) {
			if (builds.size() <= i) break;
			
			System.out.println(builds.get(i));
		}
	}
}
