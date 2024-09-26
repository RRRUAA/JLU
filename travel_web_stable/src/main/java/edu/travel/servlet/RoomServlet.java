package edu.travel.servlet;

import com.jspsmart.upload.*;
import edu.travel.entity.Room;
import edu.travel.service.RoomService;
import edu.travel.service.impl.RoomServiceImpl;
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

@WebServlet(name = "RoomServlet")
public class RoomServlet extends BaseServlet {

	RoomService rs = new RoomServiceImpl();


	/**
	 * 去修改房间
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toUpdateRoom(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = WebUtils.parseInt(request.getParameter("id"), 0);
		Room room = rs.findRoomById(id);
		request.setAttribute("data", room);
		request.getRequestDispatcher("/WEB-INF/view/room/update.jsp").forward(request, response);
	}

	/**
	 * 删除房间
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteRoom(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "no";
		int id = WebUtils.parseInt(request.getParameter("id"), 0);

		rs.deleteRoom(id);
		message = "yes";
		response.getWriter().print(message);

	}

	/**
	 * 添加房间
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addRoom(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 处理注册
		String message = "no";
		/*	Room room = WebUtils.toBean(request.getParameterMap(), Room.class);*/
		/*	Room.setCreate_date(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));*/
		/*int id= WebUtils.parseInt(request.getParameter("id"), 0);*/
		Room room = fileUpload(request);
		/*room.setId(id);*/
		rs.addRoom(room);
		message = "yes";
		response.getWriter().print(message);
	}


	private Room fileUpload(HttpServletRequest request) {
		Room room = new Room();
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					BeanUtils.setProperty(room, name, value);
				} else {
					String filename = item.getName();
					String savefilename = makeFileName(filename);
					/*String savepath="D:\\Program Files\\eclipse_workspace\\travel_web\\WebContent\\upload\\";*/
					/*		String savepath=WebUtils.getRealPath();//物理路径
					 */                    //String savepath= this.getServletContext().getRealPath("/upload");
					String savepath = WebUtils.getRealPath();
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
					room.setPhoto(savefilename);
				}
			}
			return room;
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
	 * 去新增房间
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toAddRoom(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/view/room/add.jsp").forward(request, response);
	}

	/**
	 * 门票列表
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void RoomList(HttpServletRequest request, HttpServletResponse response)
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
		List<Room> list = rs.getRoomPage(pageNum, pageSize, map);
		/*for(Room Room : list) {
			Room.setScenic(ScenicService.findScenicById(Room.getSid().toString()));
		}*/
		// 携带参数到页面
		request.setAttribute("list", list); // 绑定参数
		int nums = rs.queryRoomCount(map); // 查询总数
		// 计算总页数
		int totalPage = (nums % pageSize == 0) ? (nums / pageSize) : (nums / pageSize + 1);
		request.setAttribute("cp", pageNum); // 当前页
		request.setAttribute("tp", totalPage); // 总页数
		request.setAttribute("key", key); // 总页数
		// 条件 值1：值2
		request.getRequestDispatcher("/WEB-INF/view/room/list.jsp").forward(request, response); // 页面转发
	}


	/**
	 * 修改房间
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateRoom(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 查询个人信息
		String message = "no";
		Room room = fileUpload(request);
		/* Room room = WebUtils.toBean(request.getParameterMap(), Room.class);*/
		rs.updateRoom(room);
		response.sendRedirect(request.getContextPath() + "/RoomServlet?action=RoomList");//重定向防止重复提交
	/*	message = "yes";
		response.getWriter().print(message);*/

	}


	//修改
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//更改
		SmartUpload su = new SmartUpload();
		//初使化
		su.initialize(this.getServletConfig(), request, response);
		//上传过程
		try {
			su.upload();
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取上传的文件对象
		Files fs = su.getFiles();
		File f = fs.getFile(0);
		//获取上传的文件名称
		String fname = f.getFileName();
		//String savepath= this.getServletContext().getRealPath("/upload");//上传路径
		String savepath = WebUtils.getRealPath();
		try {

			su.save(savepath);
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Request req1 = su.getRequest();
		String title = req1.getParameter("title");
		String name = req1.getParameter("name");
		String price = req1.getParameter("price");
		String category = req1.getParameter("category");
		String phone = req1.getParameter("phone");
		String note = req1.getParameter("note");
		String photo = req1.getParameter("photo");
		Room room = new Room();
		/* room.set*/
		room.setId(Integer.parseInt(req1.getParameter("id")));
		room.setNote(note);
		room.setTitle(title);
		room.setName(name);
		room.setPrice(Integer.parseInt(price));
		room.setCategory(category);
		room.setPhone(phone);
		if (f.isMissing()) {//如果没有上传就还用原来的图片
			System.out.print("没有上传任何文件" + fname);
			room.setPhoto(photo);
		} else {
			room.setPhoto(fname);
		}
		try {
			rs.updateRoom(room);
		} catch (Exception e) {
		}
		;
		response.sendRedirect(request.getContextPath() + "/RoomServlet?action=RoomList");//重定向防止重复提交


	}


}
