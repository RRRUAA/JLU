package edu.travel.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        /*HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        HttpSession session = request.getSession();

        Object admin = session.getAttribute("admin");
        PrintWriter out = response.getWriter();
        if(admin != null){

            chain.doFilter(request,response);

        } else{

            out.println("您还未登陆，三秒钟后跳转至登录页面");

            //out.println("<script language='javascript'>alert('你还未登录');");

            response.setHeader("refresh","3;/WEB-INF/view/login.jsp");

            //response.sendRedirect("/pages/users/login.jsp");

            //request.getRequestDispatcher("/pages/users/login.jsp").forward(request,response);

        }*/

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String url = request.getParameter("action");
		if (url.indexOf("/LoginServlet?action=login") >= 0) {
			return;
		}
		Object user = request.getSession().getAttribute("admin");

		if (user == null) {
			//未登录
			response.sendRedirect("index.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig config) throws ServletException {

	}

}
