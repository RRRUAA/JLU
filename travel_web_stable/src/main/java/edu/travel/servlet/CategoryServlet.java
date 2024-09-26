package edu.travel.servlet;

import edu.travel.entity.Category;
import edu.travel.service.CategoryService;
import edu.travel.service.impl.CategoryServiceImpl;
import edu.travel.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CategoryServlet")
public class CategoryServlet extends BaseServlet {

	CategoryService cs = new CategoryServiceImpl();


	/**
	 * 去修改景点标签
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toUpdateCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Category category = cs.findCategoryById(id);
		request.setAttribute("data", category);
		request.getRequestDispatcher("/WEB-INF/view/category/update.jsp").forward(request, response);
	}

	/**
	 * 删除景点标签
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "no";
		String id = request.getParameter("id");
		cs.deleteCategory(id);
		message = "yes";
		response.getWriter().print(message);

	}

	/**
	 * 添加景点标签
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 处理注册
		String message = "no";
		Category category = WebUtils.toBean(request.getParameterMap(), Category.class);

		cs.addCategory(category);
		message = "yes";
		response.getWriter().print(message);

	}


	/***
	 * 去新增景点标签
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toAddCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/category/add.jsp").forward(request, response);
	}

	/**
	 * 景点标签列表
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void CategoryList(HttpServletRequest request, HttpServletResponse response)
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
		List<Category> list = cs.getCategoryPage(pageNum, pageSize, map);
		// 携带参数到页面
		request.setAttribute("list", list); // 绑定参数
		int nums = cs.queryCategoryCount(map); // 查询总数
		// 计算总页数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.setAttribute("cp", pageNum); // 当前页
		request.setAttribute("tp", totalPage); // 总页数
		request.setAttribute("key", key); // 总页数
		// 条件 值1：值2
		request.getRequestDispatcher("/WEB-INF/view/category/list.jsp").forward(request, response); // 页面转发
	}

	/**
	 * 修改景点标签
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 查询个人信息
		String message = "no";
		Category category = WebUtils.toBean(request.getParameterMap(), Category.class);
		cs.updateCategory(category);
		message = "yes";
		response.getWriter().print(message);

	}


}
