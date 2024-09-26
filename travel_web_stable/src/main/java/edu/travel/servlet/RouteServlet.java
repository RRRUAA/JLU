package edu.travel.servlet;

import edu.travel.entity.Route;
import edu.travel.entity.User;
import edu.travel.service.RouteService;
import edu.travel.service.UserService;
import edu.travel.service.impl.RouteServiceImpl;
import edu.travel.service.impl.UserServiceImpl;
import edu.travel.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "RouteServlet")
public class RouteServlet extends BaseServlet {

	RouteService rs = new RouteServiceImpl();
	UserService userService = new UserServiceImpl();


	/**
	 * 去修改路线
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toUpdateRoute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = WebUtils.parseInt(request.getParameter("id"), 0);
		Route Route = rs.findRouteById(id);
		request.setAttribute("data", Route);
		request.getRequestDispatcher("/WEB-INF/view/route/update.jsp").forward(request, response);
	}

	/**
	 * 删除路线
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteRoute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "no";
		int id = WebUtils.parseInt(request.getParameter("id"), 0);
		rs.deleteRoute(id);
		message = "yes";
		response.getWriter().print(message);

	}

	/**
	 * 添加路线
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addRoute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 处理添加
		User user = (User) request.getSession().getAttribute("user");

		if (user != null) {
			Route route = WebUtils.toBean(request.getParameterMap(), Route.class);
			route.setU_id(user.getId());
			rs.addRoute(route);
		} else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('please login ');location.href='LoginServlet?action=toLogin';");
			out.write("</script>");
			out.close();
		}

		String message = "no";


		message = "yes";
		response.getWriter().print(message);

	}


	/***
	 * 去新增路线
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toAddRoute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/client/add_route1.jsp").forward(request, response);
	}

	/**
	 * 门票列表
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void RouteList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 跳转到社长列表界面
		String p = request.getParameter("p");// 接收页码
		String key = request.getParameter("key");// 接收页码
		System.out.print(p);
		int pageSize = 6;// 每页显示5条
		int pageNum = 1; // 默认第一页
		if (p != null) {
			pageNum = Integer.parseInt(p);
		}
		Map map = new HashMap<String,Object>();
		map.put("key", key);
		// 调用分页查询
		List<Route> lists = rs.getRoutePage(pageNum, pageSize, map);
		for (Route r : lists) {
			User u = userService.findUserById(String.valueOf(r.getU_id()));
			r.setUser(u);

		}
		// 携带参数到页面
		request.setAttribute("list", lists); // 绑定参数
		int nums = rs.queryRouteCount(map); // 查询总数
		// 计算总页数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.setAttribute("cp", pageNum); // 当前页
		request.setAttribute("tp", totalPage); // 总页数
		request.setAttribute("key", key); // 总页数
		// 条件 值1：值2
		request.getRequestDispatcher("/WEB-INF/view/route/list.jsp").forward(request, response); // 页面转发
	}

	/**
	 * 修改路线
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateRoute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 查询个人信息
		String message = "no";
		Route Route = WebUtils.toBean(request.getParameterMap(), Route.class);
		rs.updateRoute(Route);
		message = "yes";
		response.getWriter().print(message);

	}


}
