package edu.travel.servlet;

import edu.travel.entity.*;
import edu.travel.service.CommentsService;
import edu.travel.service.OrdersService;
import edu.travel.service.TicketService;
import edu.travel.service.impl.CommentsServiceImpl;
import edu.travel.service.impl.OrdersServiceImpl;
import edu.travel.service.impl.TicketServiceImpl;
import edu.travel.utils.WebUtils;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CommentsServlet")
public class CommentsServlet extends BaseServlet {

	CommentsService cs = new CommentsServiceImpl();
	OrdersService os = new OrdersServiceImpl();
	TicketService ts = new TicketServiceImpl();

	/**
	 * 删除评论
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteComments(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "no";
		String id = request.getParameter("id");
		cs.deleteComments(id);
		message = "yes";
		response.getWriter().print(message);

	}

	/**
	 * 添加评论
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addComments(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 进行评论
		User user = (User) request.getSession().getAttribute("user");
		int oid = Integer.parseInt(request.getParameter("oid"));
		Orders orders = os.findOrdersById(request.getParameter("oid"));
		Ticket ticket = ts.findTicketById(orders.getTid().toString());
		System.out.print(oid);
		String message = "no";
		if (user != null) {
			Comments comments = WebUtils.toBean(request.getParameterMap(), Comments.class);
			comments.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
			comments.setUid(user.getId());
			comments.setUname(user.getRealname());
			comments.setSid(ticket.getSid());
			comments.setUphoto(user.getPhoto());
			cs.addComments(comments);
			os.updateOrdersStatus(oid);//已经评论过的订单不可以在评论了
			message = "yes";
		}
		response.getWriter().print(message);

	}


	/**
	 * 评论列表
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void CommentsList(HttpServletRequest request, HttpServletResponse response)
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
		List<Comments> list = cs.getCommentsPage(pageNum, pageSize, map);
		// 携带参数到页面
		request.setAttribute("list", list); // 绑定参数
		int nums = cs.queryCommentsCount(map); // 查询总数
		// 计算总页数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.setAttribute("cp", pageNum); // 当前页
		request.setAttribute("tp", totalPage); // 总页数
		request.setAttribute("key", key); // 总页数
		// 条件 值1：值2
		request.getRequestDispatcher("/WEB-INF/view/comments/list.jsp").forward(request, response); // 页面转发
	}


	protected void selectStaticsComments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//处理注册
		List<Statics> statics = cs.selectStaticsComments();
		JSONArray jsonArray = new JSONArray();
		for (Statics ss : statics) {
			Map map = new HashMap();
			map.put("names", ss.getNames());
			map.put("counts", ss.getCounts());
			jsonArray.put(map);
		}
		response.setContentType("text/html;charset=utf-8");//改编码
		response.getWriter().print(jsonArray);//把json字符串返回的页面

	}

}
