package edu.travel.servlet;

import edu.travel.entity.Orders;
import edu.travel.entity.Statics;
import edu.travel.entity.Ticket;
import edu.travel.entity.User;
import edu.travel.service.OrdersService;
import edu.travel.service.ScenicService;
import edu.travel.service.TicketService;
import edu.travel.service.UserService;
import edu.travel.service.impl.OrdersServiceImpl;
import edu.travel.service.impl.ScenicServiceImpl;
import edu.travel.service.impl.TicketServiceImpl;
import edu.travel.service.impl.UserServiceImpl;
import edu.travel.utils.WebUtils;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "OrdersServlet")
public class OrdersServlet extends BaseServlet {

	OrdersService OrdersService = new OrdersServiceImpl();

	TicketService TicketService = new TicketServiceImpl();

	UserService userService = new UserServiceImpl();

	ScenicService ScenicService = new ScenicServiceImpl();


	/**
	 * 删除订单
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "no";
		String id = request.getParameter("id");
		OrdersService.deleteOrders(id);
		message = "yes";
		response.getWriter().print(message);

	}

	/**
	 * 添加订单
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 
		User user = (User) request.getSession().getAttribute("user");
		String message = "no";
		if (user != null) {
			Orders orders = WebUtils.toBean(request.getParameterMap(), Orders.class);
			Ticket ticket = TicketService.findTicketById(orders.getTid().toString());
			if (ticket.getPrice() * orders.getQuantity() <= user.getMoney()) {
				if (ticket.getSotock() >= orders.getQuantity()) {
					orders.setUid(user.getId());
					orders.setUname(user.getRealname());
					/*orders.setTotal_price(0);*/
					orders.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
					orders.setTotal_price(Double.valueOf(ticket.getPrice() * orders.getQuantity()));
					orders.setOrdernum(getRno());
					OrdersService.addOrders(orders);
					user.setMoney(user.getMoney() - orders.getTotal_price());
					userService.addMoney(user);
					request.getSession().setAttribute("user", user);
					ticket.setSotock(ticket.getSotock() - orders.getQuantity());
					TicketService.updateTicket(ticket);
					message = "yes";
				} else {
					message = "noTickets";
				}
			} else {
				message = "noMoney";
			}

		}
		response.getWriter().print(message);

	}


	/**
	 * 订单列表
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void OrdersList(HttpServletRequest request, HttpServletResponse response)
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
		List<Orders> list = OrdersService.getOrdersPage(pageNum, pageSize, map);
		for (Orders oo : list) {
			oo.setTicket(TicketService.findTicketById(oo.getTid().toString()));
			oo.getTicket().setScenic(ScenicService.findScenicById(oo.getTicket().getSid().toString()));
		}
		// 携带参数到页面
		request.setAttribute("list", list); // 绑定参数
		int nums = OrdersService.queryOrdersCount(map); // 查询总数
		// 计算总页数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.setAttribute("cp", pageNum); // 当前页
		request.setAttribute("tp", totalPage); // 总页数
		request.setAttribute("key", key); // 总页数
		// 条件 值1：值2
		request.getRequestDispatcher("/WEB-INF/view/orders/list.jsp").forward(request, response); // 页面转发
	}

	public String getRno() {//生成订单号
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String newDate = sdf.format(new Date());
		String result = "";
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			result += random.nextInt(10);
		}
		return newDate + result;

	}

	protected void selectStaticsOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//处理注册
		List<Statics> statics = OrdersService.selectStaticsOrders();
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
