package com.sunwayland.core.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public class GenericFileView extends AbstractView {

	// default content type

	private final static String CONTENT_TYPE = "application/json";

	// content of http response

	private String responseContent;

	public GenericFileView() {
		super();
		setContentType(CONTENT_TYPE);
		
	}

	@Override
	public void setContentType(String contentType) {
		super.setContentType(contentType);
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
             HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType(getContentType());

		response.getWriter().write(this.responseContent);

		response.getWriter().close();

	}

	public void setResponseContent(String responseContent) {

		this.responseContent = responseContent;
		
          
	}

	 

}
