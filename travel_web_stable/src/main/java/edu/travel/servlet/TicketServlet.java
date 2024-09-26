package edu.travel.servlet;

import edu.travel.entity.Scenic;
import edu.travel.entity.Ticket;
import edu.travel.service.ScenicService;
import edu.travel.service.TicketService;
import edu.travel.service.impl.ScenicServiceImpl;
import edu.travel.service.impl.TicketServiceImpl;
import edu.travel.utils.WebUtils;

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

@WebServlet(name = "TicketServlet")
public class TicketServlet extends BaseServlet {

	TicketService TicketService = new TicketServiceImpl();

	ScenicService ScenicService = new ScenicServiceImpl();


	/**
	 * 去修改门票
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toUpdateTicket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Ticket Ticket = TicketService.findTicketById(id);
		request.setAttribute("data", Ticket);
		List<Scenic> scenicList = ScenicService.selectList();
		request.setAttribute("scenicList", scenicList);
		request.getRequestDispatcher("/WEB-INF/view/ticket/update.jsp").forward(request, response);
	}

	/**
	 * 删除门票
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteTicket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "no";
		String id = request.getParameter("id");
		TicketService.deleteTicket(id);
		message = "yes";
		response.getWriter().print(message);

	}

	/**
	 * 添加门票
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addTicket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 处理注册
		String message = "no";
		Ticket Ticket = WebUtils.toBean(request.getParameterMap(), Ticket.class);
		Ticket.setCreate_date(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		TicketService.addTicket(Ticket);
		message = "yes";
		response.getWriter().print(message);

	}


	/***
	 * 去新增门票
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toAddTicket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Scenic> scenicList = ScenicService.selectList();
		request.setAttribute("scenicList", scenicList);
		request.getRequestDispatcher("/WEB-INF/view/ticket/add.jsp").forward(request, response);
	}

	/**
	 * 门票列表
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void TicketList(HttpServletRequest request, HttpServletResponse response)
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
		List<Ticket> list = TicketService.getTicketPage(pageNum, pageSize, map);
		for (Ticket ticket : list) {
			ticket.setScenic(ScenicService.findScenicById(ticket.getSid().toString()));
		}
		// 携带参数到页面
		request.setAttribute("list", list); // 绑定参数
		int nums = TicketService.queryTicketCount(map); // 查询总数
		// 计算总页数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.setAttribute("cp", pageNum); // 当前页
		request.setAttribute("tp", totalPage); // 总页数
		request.setAttribute("key", key); // 总页数
		// 条件 值1：值2
		request.getRequestDispatcher("/WEB-INF/view/ticket/list.jsp").forward(request, response); // 页面转发
	}

	/**
	 * 修改门票
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateTicket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 查询个人信息
		String message = "no";
		Ticket Ticket = WebUtils.toBean(request.getParameterMap(), Ticket.class);
		TicketService.updateTicket(Ticket);
		message = "yes";
		response.getWriter().print(message);

	}


}
