package by.training.carrent.controller.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import by.training.carrent.controller.command.PagePath;

public class PaginationTag extends TagSupport {
	private static final long serialVersionUID = -8639846713001679127L;
	private static final String PAGE_TYPE_HOME = "home";
	private static final String PAGE_TYPE_ORDERS = "orders";
	private static final String PAGE_TYPE_ADMIN_USERS = "admin_users";
	private static final String PAGE_TYPE_ADMIN_ORDERS = "admin_orders";
	private static final String PAGE_TYPE_ADMIN_CARS = "admin_cars";

	private int currentPage;
	private int maxPage;
	private String pageType;

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public void setPageType(String pageType) {
		switch (pageType) {
		case PAGE_TYPE_HOME -> this.pageType = PagePath.HOME_PAGE_REDIRECT;
		case PAGE_TYPE_ORDERS -> this.pageType = PagePath.ORDERS_PAGE_REDIRECT;
		case PAGE_TYPE_ADMIN_USERS -> this.pageType = PagePath.ADMIN_USERS_REDIRECT;
		case PAGE_TYPE_ADMIN_ORDERS -> this.pageType = PagePath.ADMIN_ORDERS_REDIRECT;
		case PAGE_TYPE_ADMIN_CARS -> this.pageType = PagePath.ADMIN_CARS_REDIRECT;
		default -> this.pageType = PagePath.HOME_PAGE_REDIRECT;
		}
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			if (currentPage > 0 && maxPage > 0) {
				JspWriter writer = pageContext.getOut();
				StringBuilder stringBuilder = new StringBuilder("<ul class='pagination justify-content-md-center'>");
				for (int i = 1; i <= maxPage; i++) {
					if (i == currentPage) {
						stringBuilder.append("<li class='page-item active'><a class='page-link' href='")
								.append(pageType).append("&page=").append(i).append("'>").append(i).append("</a></li>");
					//} else if (i == 1 || i == maxPage) {
					//	stringBuilder.append("<li class='page-item'><a class='page-link' href='").append(pageType)
					//			.append("&page=").append(i).append("'>").append(i).append("</a></li>");
					} else {
						stringBuilder.append("<li class='page-item'><a class='page-link' href='").append(pageType)
								.append("&page=").append(i).append("'>").append(i).append("</a></li>");
					}
				}
				stringBuilder.append("</ul>");
				writer.write(stringBuilder.toString());
			}
			return SKIP_BODY;
		} catch (IOException e) {
			throw new JspException("PaginationTag error: {}", e);
		}
	}
}
