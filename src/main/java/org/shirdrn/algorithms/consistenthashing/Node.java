package org.shirdrn.algorithms.consistenthashing;

import java.io.Serializable;

/**
 * Node abstraction.
 * 
 * @author Shirdrn
 */
public abstract class Node implements Serializable, Comparable<Node> {

	private static final long serialVersionUID = 6868106353023128687L;
	protected final int id;
	
	public Node(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public int compareTo(Node o) {
		if(id < o.id) {
			return -1;
		} else if(id > o.id) {
			return 1;
		}
		return 0;
	}
	
}