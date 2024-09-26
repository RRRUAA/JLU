package edu.travel.servlet;

import edu.travel.entity.Room;
import edu.travel.entity.User;
import edu.travel.entity.Ydxx;
import edu.travel.service.RoomService;
import edu.travel.service.UserService;
import edu.travel.service.YdxxService;
import edu.travel.service.impl.RoomServiceImpl;
import edu.travel.service.impl.UserServiceImpl;
import edu.travel.service.impl.YdxxServiceImpl;
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

@WebServlet(name = "YdxxServlet")
public class YdxxServlet extends BaseServlet {

	YdxxService ys = new YdxxServiceImpl();


	RoomService rs = new RoomServiceImpl();
	UserService userService = new UserServiceImpl();

	/**
	 * 删除预订
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteYdxx(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "no";
		int id = WebUtils.parseInt(request.getParameter("id"), 0);
		ys.deleteYdxx(id);
		message = "yes";
		response.getWriter().print(message);

	}

	/**
	 * 添加预订
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addYdxx(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 处理注册
		User user = (User) request.getSession().getAttribute("user");

		if (user != null) {
			Ydxx yyxx = WebUtils.toBean(request.getParameterMap(), Ydxx.class);
			Room room = rs.findRoomById(yyxx.getRoom_id());
			yyxx.setYdbh(new WebUtils().getRno());
			yyxx.setStatus(0);
			yyxx.setTotal_price(yyxx.getDays() * room.getPrice());
			ys.addYdxx(yyxx);
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
	 * 去新增预订
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toAddYdxx(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("id", request.getParameter("id")); // 当前页
		request.getRequestDispatcher("/WEB-INF/view/client/add_ydxx.jsp").forward(request, response);
	}

	/**
	 * 门票列表
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void YdxxList(HttpServletRequest request, HttpServletResponse response)
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
		List<Ydxx> list = ys.getYdxxPage(pageNum, pageSize, map);
		for (Ydxx y : list) {
			User u = userService.findUserById(String.valueOf(y.getUid()));
			Room room = rs.findRoomById(y.getRoom_id());
			y.setUser(u);
			y.setRoom(room);
		}

		// 携带参数到页面
		request.setAttribute("list", list); // 绑定参数

		int nums = ys.queryYdxxCount(map); // 查询总数
		// 计算总页数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.setAttribute("cp", pageNum); // 当前页
		request.setAttribute("tp", totalPage); // 总页数
		request.setAttribute("key", key); // 总页数
		// 条件 值1：值2
		request.getRequestDispatcher("/WEB-INF/view/ydxx/list.jsp").forward(request, response); // 页面转发
	}


}
