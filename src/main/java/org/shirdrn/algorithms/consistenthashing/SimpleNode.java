package org.shirdrn.algorithms.consistenthashing;

public class SimpleNode extends Node {

	private static final long serialVersionUID = -9070408992608374975L;
	private final String host;
	private String ipAddress;
	
	public SimpleNode(int id, String host) {
		super(id);
		this.host = host;
	}
	
	public SimpleNode(int id, String host, String ipAddress) {
		this(id, host);
		this.ipAddress = ipAddress;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[").append(id).append(", ")
		.append(host).append(", ")
		.append(ipAddress).append("]");
		return sb.toString();
	}
	
}
