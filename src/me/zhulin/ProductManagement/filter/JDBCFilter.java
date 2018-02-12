package me.zhulin.ProductManagement.filter;

import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import me.zhulin.ProductManagement.conn.ConnectionUtils;
import me.zhulin.ProductManagement.utils.MyUtils;

/**
 * Servlet Filter implementation class JDBCFilter
 */
@WebFilter("/*")
public class JDBCFilter implements Filter {

    /**
     * Default constructor. 
     */
    public JDBCFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

        HttpServletRequest req = (HttpServletRequest) request;
 
        // Only open connections for the special requests.
        // (For example, the path to the servlet, JSP, ..)
        // 
        // Avoid open connection for commons request.
        // (For example: image, css, javascript,... )
        // 
        if(this.needJDBC(req)) {
        	System.out.println("Open Connection" + req.getServletPath());
        	
        	Connection conn = null;
        	try {
        		conn = ConnectionUtils.getConnection();
        		
        		conn.setAutoCommit(false);
        		
        		MyUtils.storeConnection(request, conn);
        		
        		// pass the request along the filter chain
        		chain.doFilter(request, response);
        		conn.commit();
        	} catch(Exception e) {
        		e.printStackTrace();
        		ConnectionUtils.rollbackQuietly(conn);
        	} finally {
        		ConnectionUtils.closeQuietly(conn);
        	}
        }
	}
	
	private boolean needJDBC(HttpServletRequest request) {
        // 
        // Servlet Url-pattern: /spath/*
        // 
        // => /spath
		String servletPath = request.getServletPath();
		// => /abc/mnp
		String pathInfo = request.getPathInfo();
		
		String urlPattern = servletPath;
		
		if(pathInfo != null) {
			// => /spath/*
			urlPattern = servletPath + "/*";
		}
        // Key: servletName.
        // Value: ServletRegistration
        Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
                .getServletRegistrations();
 
        // Collection of all servlet in your Webapp.
        Collection<? extends ServletRegistration> values = servletRegistrations.values();
        for (ServletRegistration sr : values) {
            Collection<String> mappings = sr.getMappings();
            if (mappings.contains(urlPattern)) {
                return true;
            }
        }
        return false;
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
