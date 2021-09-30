package by.training.carrent.controller.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import by.training.carrent.controller.command.PagePath;

public class PaginationTag extends TagSupport {
	private int currentPage;
	private int maxPage;

	public void setPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
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
								.append(PagePath.HOME_PAGE_REDIRECT).append("&page=").append(i).append("'>").append(i)
								.append("</a></li>");
					} else {
						if (i == 1 || i == maxPage) {
							stringBuilder.append("<li class='page-item'><a class='page-link' href='")
									.append(PagePath.HOME_PAGE_REDIRECT).append("&page=").append(i).append("'>")
									.append(i).append("</a></li>");
						} else {
							if ((currentPage - 3) < i && (currentPage + 3) > i) {
								stringBuilder.append("<li class='page-item'><a class='page-link' href='")
										.append(PagePath.HOME_PAGE_REDIRECT).append("&page=").append(i).append("'>")
										.append(i).append("</a></li>");
							} else {
								stringBuilder.append(".");
							}
						}
					}
				}
				stringBuilder.append("</ul>");
				writer.write(stringBuilder.toString());
			}
			return SKIP_BODY;
		} catch (IOException e) {
			throw new JspException("PaginationTag error: " + e.getMessage(), e);
		}
	}
}
