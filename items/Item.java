package items;

import util.Effect;
import util.Action;

public class Item {
	public final String name;
	
	public Effect 	effect = null,
					passive = null,
					uniquePassive = null;
	public Action active = null;
	public final int cost;
	
	protected Item(String name, int cost) {
		this.name = name;
		this.cost = cost;
	}
	
	public String toString() {
		return name;
	}
}
