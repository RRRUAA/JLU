package edu.travel.servlet;

import edu.travel.entity.News;
import edu.travel.service.NewsService;
import edu.travel.service.impl.NewsServiceImpl;
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

@WebServlet(name = "NewsServlet")
public class NewsServlet extends BaseServlet {

	NewsService NewsService = new NewsServiceImpl();


	/**
	 * 去修改景点资讯
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toUpdateNews(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		News News = NewsService.findNewsById(id);
		request.setAttribute("data", News);
		request.getRequestDispatcher("/WEB-INF/view/news/update.jsp").forward(request, response);
	}

	/**
	 * 删除景点资讯
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteNews(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "no";
		String id = request.getParameter("id");
		NewsService.deleteNews(id);
		message = "yes";
		response.getWriter().print(message);

	}

	/**
	 * 添加景点资讯
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addNews(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 处理注册
		String message = "no";
		News News = WebUtils.toBean(request.getParameterMap(), News.class);
		News.setFbsj(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		NewsService.addNews(News);
		message = "yes";
		response.getWriter().print(message);

	}


	/***
	 * 去新增景点资讯
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toAddNews(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/news/add.jsp").forward(request, response);
	}

	/**
	 * 景点资讯列表
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void NewsList(HttpServletRequest request, HttpServletResponse response)
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
		List<News> list = NewsService.getNewsPage(pageNum, pageSize, map);
		// 携带参数到页面
		request.setAttribute("list", list); // 绑定参数
		int nums = NewsService.queryNewsCount(map); // 查询总数
		// 计算总页数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.setAttribute("cp", pageNum); // 当前页
		request.setAttribute("tp", totalPage); // 总页数
		request.setAttribute("key", key); // 总页数
		// 条件 值1：值2
		request.getRequestDispatcher("/WEB-INF/view/news/list.jsp").forward(request, response); // 页面转发
	}

	/**
	 * 修改景点资讯
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateNews(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 查询个人信息
		String message = "no";
		News News = WebUtils.toBean(request.getParameterMap(), News.class);
		NewsService.updateNews(News);
		message = "yes";
		response.getWriter().print(message);

	}


}
