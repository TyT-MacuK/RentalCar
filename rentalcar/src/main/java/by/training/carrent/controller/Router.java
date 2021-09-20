package by.training.carrent.controller;

public class Router {
	public enum RouterType {
		FORWARD, REDIRECT
	}

	private RouterType type;
	private String pagePath;

	public Router(String pagePath) {
		type = RouterType.FORWARD;
		this.pagePath = pagePath;
	}

	public RouterType getType() {
		return type;
	}

	public void setRedirect() {
		type = RouterType.REDIRECT;
	}

	public String getPagePath() {
		return pagePath;
	}

	public void setPageUri(String pagePath) {
		this.pagePath = pagePath;
	}

}
