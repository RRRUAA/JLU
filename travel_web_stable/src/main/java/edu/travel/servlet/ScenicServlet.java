package edu.travel.servlet;

import edu.travel.entity.Category;
import edu.travel.entity.Scenic;
import edu.travel.service.CategoryService;
import edu.travel.service.ScenicService;
import edu.travel.service.impl.CategoryServiceImpl;
import edu.travel.service.impl.ScenicServiceImpl;
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
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "ScenicServlet")
public class ScenicServlet extends BaseServlet {
	CategoryService cs = new CategoryServiceImpl();
	ScenicService ScenicService = new ScenicServiceImpl();

	/**
	 * 去修改景点
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toUpdateScenic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Scenic Scenic = ScenicService.findScenicById(id);
		List<Category> categories = cs.selectAll();
		request.setAttribute("categories", categories);
		request.setAttribute("data", Scenic);
		request.getRequestDispatcher("/WEB-INF/view/scenic/update.jsp").forward(request, response);
	}

	/**
	 * 删除景点
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteScenic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "no";
		String id = request.getParameter("id");
		ScenicService.deleteScenic(id);
		message = "yes";
		response.getWriter().print(message);

	}

	/**
	 * 添加景点
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addScenic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 处理注册
		String message = "no";
		Scenic Scenic = fileUpload(request);
		Scenic.setCreate_date(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		ScenicService.addScenic(Scenic);
		message = "yes";
		response.getWriter().print(message);

	}

	/**
	 * 上传文件工具方法
	 *
	 * @param request
	 * @return
	 */
	private Scenic fileUpload(HttpServletRequest request) {
		Scenic Scenic = new Scenic();
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					BeanUtils.setProperty(Scenic, name, value);
				} else {
					String filename = item.getName();
					String savefilename = makeFileName(filename);
					//String savepath="D:\\Program Files\\eclipse_workspace\\travel_web\\WebContent\\upload\\";
					//String savepath = "D:\\upload\\";
					String savepath = WebUtils.getRealPath();//物理路径
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
					Scenic.setPhoto(savefilename);
				}
			}
			return Scenic;
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
	 * 去新增景点
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toAddScenic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> categories = cs.selectAll();
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/WEB-INF/view/scenic/add.jsp").forward(request, response);
	}

	/**
	 * 景点列表
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void ScenicList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 跳转到社长列表界面
		String p = request.getParameter("p");// 接收页码
		String key = request.getParameter("key");// 接收页码
		System.out.print(p);
		int pageSize = 4;// 每页显示5条
		int pageNum = 1; // 默认第一页
		if (p != null) {
			pageNum = Integer.parseInt(p);
		}
		Map map = new HashMap<String,Object>();
		map.put("key", key);
		// 调用分页查询
		List<Scenic> list = ScenicService.getScenicPage(pageNum, pageSize, map);
		for (Scenic scenic : list) {
			Category category = cs.findCategoryById(String.valueOf(scenic.getC_id()));
			scenic.setCategory(category);
		}
		// 携带参数到页面
		request.setAttribute("list", list); // 绑定参数
		int nums = ScenicService.queryScenicCount(map); // 查询总数
		// 计算总页数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.setAttribute("cp", pageNum); // 当前页
		request.setAttribute("tp", totalPage); // 总页数
		request.setAttribute("key", key); // 总页数
		// 条件 值1：值2
		request.getRequestDispatcher("/WEB-INF/view/scenic/list.jsp").forward(request, response); // 页面转发
	}

	/**
	 * 修改景点
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateScenic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 查询个人信息
		String message = "no";
		Scenic Scenic = WebUtils.toBean(request.getParameterMap(), Scenic.class);
		ScenicService.updateScenic(Scenic);
		message = "yes";
		response.getWriter().print(message);

	}


}
