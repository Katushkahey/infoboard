package com.tsystems.logisticsProject.view;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class AbstractView {

    protected String getParameter(String name) {
        return getHttpServletRequest().getParameter(name);
    }

    protected HttpServletRequest getHttpServletRequest() {
        final FacesContext context = FacesContext.getCurrentInstance();
        return (HttpServletRequest) context.getExternalContext().getRequest();
    }

}
