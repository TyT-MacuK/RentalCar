package by.training.carrent.controller;

public class Router {
	public enum RouterType {
		FORWARD, REDIRECT
	}

	private RouterType type;
	private String pageUri;

	public Router(RouterType type, String pageUri) {
		this.type = type;
		this.pageUri = pageUri;
	}

	public RouterType getType() {
		return type;
	}

	public void setType(RouterType type) {
		this.type = type;
	}

	public String getPageUri() {
		return pageUri;
	}

	public void setPageUri(String pageUri) {
		this.pageUri = pageUri;
	}

}
