package edu.travel.servlet;

import edu.travel.entity.*;
import edu.travel.service.*;
import edu.travel.service.impl.*;
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

@WebServlet(name = "IndexServlet")
public class IndexServlet extends BaseServlet {

	UserService userService = new UserServiceImpl();

	NewsService ns = new NewsServiceImpl();

	ScenicService scenicService = new ScenicServiceImpl();

	TicketService ts = new TicketServiceImpl();

	SituationService situationService = new SituationServiceImpl();

	OrdersService os = new OrdersServiceImpl();

	CommentsService cs = new CommentsServiceImpl();

	RoomService rService = new RoomServiceImpl();
	RouteService rs = new RouteServiceImpl();
	YdxxService ydxxService = new YdxxServiceImpl();

	CategoryService categoryService = new CategoryServiceImpl();

	//推荐景点
	protected void toShouye(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String p = request.getParameter("p");// 接收页码
		System.out.print(p);
		int pageSize = 4;// 每页显示5条
		int pageNum = 1; // 默认第一页
		if (p != null) {
			pageNum = Integer.parseInt(p);
		}

		List<Scenic> scenics = scenicService.getScenicList(pageNum, pageSize);
		for (Scenic re : scenics) {
			Category category = categoryService.findCategoryById(String.valueOf(re.getC_id()));
			re.setCategory(category);
		}
		// 携带参数到页面
		List<Category> categories = categoryService.selectAll();
		request.setAttribute("categories", categories);
		int nums = scenicService.queryScenicAllCount();
		// 计算总页数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.setAttribute("cp", pageNum); // 当前页
		request.setAttribute("tp", totalPage); // 总页数
		request.setAttribute("list", scenics); // 绑定参数
		request.getRequestDispatcher("/WEB-INF/view/client/shouye.jsp").forward(request, response); // 页面转发
	}

	protected void toShouye2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cid = request.getParameter("c_id");// 接收页
		String p = request.getParameter("p");// 接收页码
		/*System.out.print(p+"ii");*/
		int pageSize = 4;// 每页显示5条
		int pageNum = 1; // 默认第一页
		if (p != null) {
			pageNum = Integer.parseInt(p);
		}
		int c_id = 0;
		if (cid != null) {
			c_id = Integer.parseInt(cid);
		}

		List<Scenic> scenics = scenicService.findAllByCid(pageNum, pageSize, c_id);
		System.out.print(scenics.size() + "size");
		for (Scenic re : scenics) {
			Category category = categoryService.findCategoryById(String.valueOf(re.getC_id()));
			re.setCategory(category);
		}
		// 携带参数到页面
		List<Category> categories = categoryService.selectAll();
		request.setAttribute("categories", categories);
		int nums = scenicService.queryScenicCountByCid(c_id);
		// 计算总页数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.setAttribute("cp", pageNum); // 当前页
		request.setAttribute("tp", totalPage); // 总页数
		request.setAttribute("list", scenics); // 绑定参数
		request.getRequestDispatcher("/WEB-INF/view/client/shouye.jsp").forward(request, response); // 页面转发
	}

	//门票列表
	protected void toIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String p = request.getParameter("p");// 接收页码

		System.out.print(p);
		int pageSize = 4;// 每页显示5条
		int pageNum = 1; // 默认第一页
		if (p != null) {
			pageNum = Integer.parseInt(p);
		}


		List<Ticket> ticketList = ts.selectList(pageNum, pageSize);
		// 携带参数到页面
		request.setAttribute("list", ticketList); // 绑定参数
		Map map = new HashMap<String,Object>();
		int nums = ts.queryTicketCount(map); // 查询总数
		// 计算总页数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.setAttribute("cp", pageNum); // 当前页
		request.setAttribute("tp", totalPage); // 总页数

		for (Ticket tt : ticketList) {
			tt.setScenic(scenicService.findScenicById(tt.getSid().toString()));
		}
		request.setAttribute("ticketList", ticketList.size() > 8 ? ticketList.subList(0, 8) : ticketList); // 绑定参数
		request.getRequestDispatcher("/WEB-INF/view/client/index.jsp").forward(request, response); // 页面转发
	}

	//酒店详情页
	protected void toRoomDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//
		int id = WebUtils.parseInt(request.getParameter("id"), 0);
		Room room = rService.findRoomById(id);
		request.setAttribute("room", room); //绑定参数
		request.getRequestDispatcher("/WEB-INF/view/client/room_detail.jsp").forward(request, response); //页面转发
	}

	//酒店房间列表
	protected void toRoom(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sid = request.getParameter("sid");
		List<Room> rooms = rService.selectRoomListBySid(Integer.valueOf(sid));
		/*for(Room r : rooms) {
			tt.setScenic(scenicService.findScenicById(tt.getSid().toString()));
		}*/
		request.setAttribute("roomList", rooms.size() > 8 ? rooms.subList(0, 8) : rooms); // 绑定参数
		request.getRequestDispatcher("/WEB-INF/view/client/room_list.jsp").forward(request, response); // 页面转发
	}

	//旅游线路
	protected void toRoute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Route> routes = rs.selectList();
		for (Route r : routes) {
			User user = userService.findUserById(String.valueOf(r.getU_id()));
			r.setUser(user);
			/*tt.setScenic(scenicService.findScenicById(tt.getSid().toString()));*/
		}
		List<Scenic> scenics = scenicService.selectAll();
		request.setAttribute("scenicList", scenics.size() > 6 ? scenics.subList(0, 6) : scenics); //绑定参数
		request.setAttribute("routeList", routes.size() > 8 ? routes.subList(0, 8) : routes); // 绑定参数
		request.getRequestDispatcher("/WEB-INF/view/client/route_list.jsp").forward(request, response); // 页面转发
	}


	//去评论
	protected void toComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tid = request.getParameter("tid");//接受tid
		System.out.println(tid);
		Ticket ticket = ts.findTicketById(tid);
		Scenic scenic = scenicService.findScenicById(String.valueOf(ticket.getSid()));
		request.setAttribute("ticket", ticket); //门票
		request.setAttribute("scenic", scenic); //景点
		request.setAttribute("oid", request.getParameter("oid")); //订单编号
		request.getRequestDispatcher("/WEB-INF/view/client/comment.jsp").forward(request, response); //页面转发
	}

	//我的评价
	protected void toMyComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			List<Comments> comments = cs.findMyComment(String.valueOf(user.getId()));
			/* for(Comments c:comments) {
				 User u=userService.findUserById(String.valueOf(c.getUid()));
				 c.setU
			 }*/
			request.setAttribute("comments", comments); //我的评价信息
			request.getRequestDispatcher("/WEB-INF/view/client/my_comment.jsp").forward(request, response); //页面转发
		} else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('please login ');location.href='LoginServlet?action=toLogin';");
			out.write("</script>");
			out.close();
		}

	}

	//查看我的线路
	protected void toMyRoute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			List<Route> routes = rs.findMyRoute(user.getId());
			for (Route r : routes) {
				User u = userService.findUserById(String.valueOf(r.getU_id()));
				r.setUser(u);
			}
			request.setAttribute("routes", routes); //我的线路
			request.getRequestDispatcher("/WEB-INF/view/client/my_route.jsp").forward(request, response); //页面转发
		} else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('please login ');location.href='LoginServlet?action=toLogin';");
			out.write("</script>");
			out.close();
		}

	}

	//我的预订信息
	protected void toMyYdxx(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			List<Ydxx> ydxxs = ydxxService.findMyYdxx(user.getId());
			for (Ydxx y : ydxxs) {
				User u = userService.findUserById(String.valueOf(y.getUid()));
				Room room = rService.findRoomById(y.getRoom_id());
				y.setUser(u);
				y.setRoom(room);
			}
			request.setAttribute("ydxxs", ydxxs); //我的线路
			request.getRequestDispatcher("/WEB-INF/view/client/my_ydxx.jsp").forward(request, response); //页面转发
		} else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('please login ');location.href='LoginServlet?action=toLogin';");
			out.write("</script>");
			out.close();
		}

	}


	//门票大厅
	protected void toSearchIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String p = request.getParameter("p");//接收页码
		String key = request.getParameter("key");//接收页码
		String cname = request.getParameter("cname");//接收页码
		String orderBy = request.getParameter("orderBy");//接收页码
		String price1 = request.getParameter("price1");//接收页码
		String price2 = request.getParameter("price2");//接收页码
		System.out.print(p);
		int pageSize = 8;
		int pageNum = 1; //默认第一页
		if (p != null) {
			pageNum = Integer.parseInt(p);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key", key);
		map.put("cname", cname);
		map.put("orderBy", orderBy);
		map.put("price1", price1);
		map.put("price2", price2);
		List<Ticket> list = ts.getTicketPage(pageNum, pageSize, map);
		for (Ticket tt : list) {
			tt.setScenic(scenicService.findScenicById(tt.getSid().toString()));
		}
		request.setAttribute("list", list); //绑定参数
		int nums = ts.queryTicketCount(map); //查询总数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.setAttribute("cp", pageNum); //当前页
		request.setAttribute("tp", totalPage); //总页数
		request.setAttribute("key", key); //总页数
		request.setAttribute("price1", price1); //总页数
		request.setAttribute("price2", price2); //总页数
		List<News> news = ns.selectAll();
		request.setAttribute("newsList", news); //绑定参数
		request.getRequestDispatcher("/WEB-INF/view/client/more_scenic.jsp").forward(request, response); //页面转发
	}

	//我的余额
	protected void toMyYue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/client/yuechongzhi.jsp").forward(request, response); //页面转发
	}

	//去我的个人信息
	protected void toMyinfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/client/my_info.jsp").forward(request, response); //页面转发
	}

	//关于我们
	protected void toAbout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/client/about.jsp").forward(request, response); //页面转发
	}

	//联系我们
	protected void toContactUs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/client/contact_us.jsp").forward(request, response); //页面转发
	}


	protected void toRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/client/register.jsp").forward(request, response); //页面转发
	}

	//去新闻资讯界面
	protected void toNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//
		List<News> newsList = ns.selectAll();
		request.setAttribute("newsList", newsList); //绑定参数
		List<Scenic> scenics = scenicService.selectAll();
		request.setAttribute("scenicList", scenics.size() > 6 ? scenics.subList(0, 6) : scenics); //绑定参数
		request.getRequestDispatcher("/WEB-INF/view/client/news_list.jsp").forward(request, response); //页面转发
	}

	//去资讯详情页面
	protected void toNewsDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//
		String id = request.getParameter("id");
		News news = ns.findNewsById(id);
		request.setAttribute("news", news); //绑定参数
		request.getRequestDispatcher("/WEB-INF/view/client/news_detail.jsp").forward(request, response); //页面转发
	}

	//去景点详情页面
	protected void toScenic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//
		String id = request.getParameter("id");
    	
		/*//获取最近访问
		
		HttpSession session = request.getSession();
		
		//从SSESSIOn获取一下 ids
		
		List<Integer> ids = (ArrayList<Integer>)session.getAttribute("ids");
		
		if(ids == null){
			ids = new ArrayList<Integer>();
		}
			
		//最多放5， 如果多出5个将第一个删除
		if(ids.size() >= 5) {
			ids.remove(0);
		}
		
		// 添加列表里， 但只要一份
		if(id!=null && (!ids.contains(Integer.parseInt(id)))) {
			ids.add(Integer.parseInt(id));
		
		}
		//最近访问，通过获取session里的商品id进行遍历
		session.setAttribute("ids", ids);		
		ids= (ArrayList<Integer>)session.getAttribute("ids");		
		if(ids!=null) {				
			List<Scenic> lastlylist = scenicService.findAllBySid(ids);
			request.setAttribute("lastlylist", lastlylist);
		}*/

		Ticket ticket = ts.findTicketById(id);
		Scenic scenic = scenicService.findScenicById(String.valueOf(ticket.getSid()));
		request.setAttribute("ticket", ticket);
		request.setAttribute("scenic", scenic);
		List<Comments> commentsList = cs.selectCommentsListBySid(ticket.getSid());


		request.setAttribute("commentsList", commentsList);
		request.setAttribute("counts", commentsList.size()); //绑定参数

		//猜你喜欢
		String p = request.getParameter("p");// 接收页码

		System.out.print(p);
		int pageSize = 4;// 每页显示5条
		int pageNum = 1; // 默认第一页
		if (p != null) {
			pageNum = Integer.parseInt(p);
		}


		List<Scenic> scenics = scenicService.findAllByCid(pageNum, pageSize, scenic.getC_id());
		for (Scenic re : scenics) {
			Category category = categoryService.findCategoryById(String.valueOf(re.getC_id()));
			scenic.setCategory(category);
		}
		// 携带参数到页面


		int nums = scenicService.queryScenicCountByCid(scenic.getC_id());
		// 计算总页数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.setAttribute("cp", pageNum); // 当前页
		request.setAttribute("tp", totalPage); // 总页数
		request.setAttribute("list", scenics); // 绑定参数

		request.getRequestDispatcher("/WEB-INF/view/client/detail.jsp").forward(request, response); //页面转发
	}

	//去景点详情
	protected void toScenic2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//
		String id = request.getParameter("id");
		Scenic scenic = scenicService.findScenicById(id);
		request.setAttribute("scenic", scenic);
		request.getRequestDispatcher("/WEB-INF/view/client/detail2.jsp").forward(request, response); //页面转发
	}

	//下订单
	protected void toPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//
		String id = request.getParameter("id");
		System.out.println(id + "门票id");
		request.setAttribute("id", id);
		request.getRequestDispatcher("/WEB-INF/view/client/pay.jsp").forward(request, response); //页面转发
	}


	//去我的订单
	protected void toMyOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//
		User user = (User) request.getSession().getAttribute("user");
		List<Orders> ordersList = os.selectOrdersByUserId(user != null ? user.getId() : 0);
		for (Orders orders : ordersList) {
			orders.setTicket(ts.findTicketById(orders.getTid().toString()));
			orders.getTicket().setScenic(scenicService.findScenicById(orders.getTicket().getSid().toString()));
			System.out.print(orders.getTotal_price() + "000000000000");
		}
        
       /* if(cs.existsComment(ordersList.get(0)))){
        	request.setAttribute("flag", 1);//已存在评论了，就不能评论
        }else {
        	request.setAttribute("flag", 0);//未u存在评论了，可以评论
        }*/
		request.setAttribute("ordersList", ordersList);

		request.getRequestDispatcher("/WEB-INF/view/client/my_order.jsp").forward(request, response); //页面转发
	}

	protected void toSituationIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String p = request.getParameter("p");//接收页码
		String key = request.getParameter("key");//接收页码
		String cname = request.getParameter("cname");//接收页码
		String orderBy = request.getParameter("orderBy");//接收页码
		String price1 = request.getParameter("price1");//接收页码
		String price2 = request.getParameter("price2");//接收页码
		System.out.print(p);
		int pageSize = 8;
		int pageNum = 1; //默认第一页
		if (p != null) {
			pageNum = Integer.parseInt(p);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key", key);
		map.put("cname", cname);
		map.put("orderBy", orderBy);
		map.put("price1", price1);
		map.put("price2", price2);
		List<Situation> list = situationService.getSituationPage(pageNum, pageSize, map);
		for (Situation tt : list) {
			tt.setScenic(scenicService.findScenicById(tt.getSid().toString()));
		}
		request.setAttribute("list", list); //绑定参数
		int nums = situationService.querySituationCount(map); //查询总数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.setAttribute("cp", pageNum); //当前页
		request.setAttribute("tp", totalPage); //总页数
		request.setAttribute("key", key); //总页数
		request.setAttribute("price1", price1); //总页数
		request.setAttribute("price2", price2); //总页数
		List<News> news = ns.selectAll();
		request.setAttribute("newsList", news); //绑定参数
		request.getRequestDispatcher("/WEB-INF/view/client/situation.jsp").forward(request, response); //页面转发
	}

	protected void toSituation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//
		String id = request.getParameter("id");

		Situation situation = situationService.findSituationById(id);
		Scenic scenic = scenicService.findScenicById(String.valueOf(situation.getSid()));
		request.setAttribute("ticket", situation);
		request.setAttribute("scenic", scenic);
		List<Room> roomList = rService.selectRoomListBySid(situation.getSid());


		request.setAttribute("roomList", roomList);
		request.setAttribute("counts", roomList.size()); //绑定参数

		//猜你喜欢
		String p = request.getParameter("p");// 接收页码

		System.out.print(p);
		int pageSize = 4;// 每页显示5条
		int pageNum = 1; // 默认第一页
		if (p != null) {
			pageNum = Integer.parseInt(p);
		}


		List<Scenic> scenics = scenicService.findAllByCid(pageNum, pageSize, scenic.getC_id());
		for (Scenic re : scenics) {
			Category category = categoryService.findCategoryById(String.valueOf(re.getC_id()));
			scenic.setCategory(category);
		}
		// 携带参数到页面


		int nums = scenicService.queryScenicCountByCid(scenic.getC_id());
		// 计算总页数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.setAttribute("cp", pageNum); // 当前页
		request.setAttribute("tp", totalPage); // 总页数
		request.setAttribute("list", scenics); // 绑定参数

		request.getRequestDispatcher("/WEB-INF/view/client/situation_detail.jsp").forward(request, response); //页面转发
	}

}
