package edu.travel.servlet;

import edu.travel.entity.Admin;
import edu.travel.entity.User;
import edu.travel.service.UserService;
import edu.travel.service.impl.UserServiceImpl;
import edu.travel.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "UserServlet")
public class UserServlet extends BaseServlet {

	UserService userService = new UserServiceImpl();

	/**
	 * 去修改用户
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toUpdateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		User user = userService.findUserById(id);
		request.setAttribute("data", user);
		request.getRequestDispatcher("/WEB-INF/view/user/update.jsp").forward(request, response);
	}

	/**
	 * 删除用户
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "no";
		String id = request.getParameter("id");
		userService.deleteUser(id);
		message = "yes";
		response.getWriter().print(message);

	}

	/**
	 * 添加用户
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 处理注册
		String message = "no";
		User user = fileUpload(request);
		User users = userService.selectUserByPhoneAndName(user.getPhone(),user.getRealname());
		if (users == null) {
			userService.addUser(user);
			message = "yes";
		} else {
			message = "isExist";
		}
		response.getWriter().print(message);

	}

	/**
	 * 上传文件工具方法
	 *
	 * @param request
	 * @return
	 */
	private User fileUpload(HttpServletRequest request) {
		User user = new User();
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					BeanUtils.setProperty(user, name, value);
				} else {
					String filename = item.getName();
					String savefilename = makeFileName(filename);
					String savepath = "D:\\JavaEE\\javaee大作业\\旅游服务平台\\源码\\travel_web\\WebContent\\upload";
					InputStream in = item.getInputStream();
					FileOutputStream out = new FileOutputStream(savepath + "\\" + savefilename);
					int len = 0;
					byte buffer[] = new byte[1024];
					while ((len = in.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
					item.delete();
					user.setPhoto(savefilename);
				}
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private String makeFileName(String filename) {
		String ext = filename.substring(filename.lastIndexOf(".") + 1);
		return UUID.randomUUID().toString() + "." + ext;
	}

	/***
	 * 去新增用户
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toAddUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/user/add.jsp").forward(request, response);
	}

	/**
	 * 用户列表
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void UserList(HttpServletRequest request, HttpServletResponse response)
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
		List<User> list = userService.getUserPage(pageNum, pageSize, map);
		// 携带参数到页面
		request.setAttribute("list", list); // 绑定参数
		int nums = userService.queryUserCount(map); // 查询总数
		// 计算总页数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.setAttribute("cp", pageNum); // 当前页
		request.setAttribute("tp", totalPage); // 总页数
		request.setAttribute("key", key); // 总页数
		// 条件 值1：值2
		request.getRequestDispatcher("/WEB-INF/view/user/list.jsp").forward(request, response); // 页面转发
	}

	/**
	 * 修改游客信息
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 查询个人信息
		User user1 = (User) request.getSession().getAttribute("user");
		String message = "no";
		User user = WebUtils.toBean(request.getParameterMap(), User.class);
		userService.updateUser(user);
		if (user1 != null) {
			request.getSession().setAttribute("user", userService.findUserById(user.getId().toString()));
		}
		message = "yes";
		response.getWriter().print(message);

	}

	/**
	 * 去修改管理员
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toUpdateAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/admin/updatePassword.jsp").forward(request, response);
	}

	/**
	 * 修改管理员密码
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateAdminPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 查询个人信息
		String message = "no";
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		String nowpass = request.getParameter("nowpass");
		String newpass = request.getParameter("newpass");
		if (admin != null && admin.getPwd().equals(nowpass)) {
			userService.updateAdminPassword(newpass, admin.getId());
			message = "yes";
		}
		response.getWriter().print(message);
	}

	/**
	 * 充值
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addMoney(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//查询个人信息
		String message = "no";
		User user = (User) request.getSession().getAttribute("user");
		String money = request.getParameter("money");
		user.setMoney(user.getMoney() + Double.parseDouble(money));
		userService.addMoney(user);
		request.getSession().setAttribute("user", user);
		message = "yes";
		response.getWriter().print(message);
	}

}
