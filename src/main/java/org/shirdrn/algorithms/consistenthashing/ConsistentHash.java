package org.shirdrn.algorithms.consistenthashing;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Consistent Hash Algorithm.</br>
 * 
 * @author Tom White, Shirdrn
 * @param <T> node object
 * @see <a href="https://weblogs.java.net/blog/2007/11/27/consistent-hashing">Consistent Hashing</a>
 */
public class ConsistentHash<T extends Node> {

	private static final Log LOG = LogFactory.getLog(ConsistentHash.class);
	private final HashFunction hashFunction;
	private final int numberOfReplicas;
	private final SortedMap<Long, T> nodeRing = new TreeMap<Long, T>();

	public ConsistentHash(HashFunction hashFunction, int numberOfReplicas, Collection<T> nodes) {
		this.hashFunction = hashFunction;
		this.numberOfReplicas = numberOfReplicas;
		for (T node : nodes) {
			add(node);
		}
	}

	public void add(T node) {
		for (int i = 0; i < numberOfReplicas; i++) {
			nodeRing.put(hashFunction.hash(node.toString() + ":" + i), node);
		}
	}

	public void remove(T node) {
		for (int i = 0; i < numberOfReplicas; i++) {
			nodeRing.remove(hashFunction.hash(node.toString() + ":" + i));
		}
	}

	public T get(Object key) {
		if (nodeRing.isEmpty()) {
			return null;
		}
		long hash = hashFunction.hash(key);
		SortedMap<Long, T> tailMap = nodeRing.tailMap(hash);
		hash = tailMap.isEmpty() ? nodeRing.firstKey() : tailMap.firstKey();
		return nodeRing.get(hash);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("-> ");
		for(Entry<Long, T> entry : nodeRing.entrySet()) {
			sb.append("(").append(entry.getKey()).append(", ")
			.append(entry.getValue().toString()).append(") -> ");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		HashFunction hashFunction = new HashFunctionImpl();
		int numberOfReplicas = 3;
		Collection<SimpleNode> nodes = new HashSet<SimpleNode>() {
			private static final long serialVersionUID = 4789176421432596802L;
			{
				super.add(new SimpleNode(100, "node-0.shiyanjun.cn", "10.95.3.100"));
				super.add(new SimpleNode(101, "node-1.shiyanjun.cn", "10.95.3.101"));
				super.add(new SimpleNode(102, "node-2.shiyanjun.cn", "10.95.3.102"));
			}
		};
		ConsistentHash<? extends Node> ch = 
				new ConsistentHash<SimpleNode>(hashFunction, numberOfReplicas, nodes);
		LOG.info(ch);
	}
}