package edu.travel.dao.impl;

import edu.travel.dao.CategoryDao;
import edu.travel.dao.ScenicDao;
import edu.travel.entity.Category;
import edu.travel.entity.Comments;
import edu.travel.entity.Scenic;
import edu.travel.entity.Ticket;
import edu.travel.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ScenicDaoImpl implements ScenicDao {

	private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());
	private String columnName="id,names,detail,photo,create_date,c_id";

	public void trySafeDel(int id){
		//List<String> needMe = Arrays.asList("ticket", "comments");
		String sql = "select "+columnName+" from scenic where id = ? and existStatus = 0";
		List<Scenic> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Scenic>(Scenic.class), id);
			if(list == null || list.size() == 0){
				return;
			}
			sql = "select id,sid,level,price,sotock,create_date from ticket where sid = ?";
			List<Ticket> ticketList = runner.query(sql, new BeanListHandler<Ticket>(Ticket.class), id);
			if(ticketList != null && !ticketList.isEmpty()){
				return;
			}
			sql = "select id,uid,sid,content,create_time,uphoto,uname,oid from comments where sid = ?";
			List<Comments> commentsList = runner.query(sql, new BeanListHandler<Comments>(Comments.class), id);
			if(commentsList != null && !commentsList.isEmpty()){
				return;
			}
			sql = "delete from scenic where id = ?";
			runner.update(sql, id);
			CategoryDao categoryDao = new CategoryDaoImpl();
			int cid = list.get(0).getC_id();//runner.query("select c_id from scenic where id = ?",new ScalarHandler<Integer>(),id);
			categoryDao.trySafeDel(cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Scenic findScenicById(String id) {
		try {// 返回查询的信息Scenic
			return runner.query("select "+columnName+" from scenic where id=? ", new BeanHandler<Scenic>(Scenic.class),
					Integer.parseInt(id));
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void deleteScenic(String id) {
		// TODO Auto-generated method stub
		Integer ids = Integer.parseInt(id);
		try {// 执行更改
			runner.update("update scenic set existStatus=0 where id=?", ids);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void addScenic(Scenic Scenic) {
		// TODO Auto-generated method stub
		try {
			// 执行插入sql
			runner.update("insert into scenic(names,detail,photo,create_date,c_id) values (?,?,?,?,?)",
					Scenic.getNames(), Scenic.getDetail(), Scenic.getPhoto(), Scenic.getCreate_date(), Scenic.getC_id());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}


	}

	@Override
	public List<Scenic> getScenicPage(int pageNum, int pageSize, Map map) {
		String sql = "select "+columnName+" from scenic where 1 = 1 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and names like  '%" + map.get("key").toString() + "%'";
		}
		sql += " limit ?,? ";
		int startNo = (pageNum - 1) * pageSize;
		List<Scenic> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Scenic>(Scenic.class), new Object[]{startNo, pageSize});// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public int queryScenicCount(Map map) {
		String sql = "select count(*) from scenic where 1 = 1 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and names like  '%" + map.get("key").toString() + "%'";
		}
		try {
			Long count = (Long) runner.query(sql, new ScalarHandler());
			// 将long的类型转成int类型
			return count.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateScenic(Scenic Scenic) {
		// TODO Auto-generated method stub
		try {// 执行更改
			runner.update("update scenic set names=?,detail=?,c_id=? where id=?",
					Scenic.getNames(), Scenic.getDetail(), Scenic.getC_id(), Scenic.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public List<Scenic> selectList() {
		String sql = "select "+columnName+" from scenic ";
		List<Scenic> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Scenic>(Scenic.class));// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public List<Scenic> selectAll() {
		String sql = "select "+columnName+" from scenic order by id desc ";
		List<Scenic> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Scenic>(Scenic.class));// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public List<Scenic> findAllBySid(List<Integer> ids) {
		// TODO Auto-generated method stub
		List<Scenic> list = null;
		for (int i = 0; i < ids.size(); i++) {
			System.out.println(ids.size());
			String sql = "select "+columnName+" from scenic where id=? ";

			try {
				list = runner.query(sql, new BeanListHandler<Scenic>(Scenic.class), ids.get(i));// 添加实体类的适配器进行类的反射

			} catch (SQLException e) {// 捕获异常
				throw new RuntimeException(e);// 抛出运行异常
			}
		}
		return list;
	}

	@Override
	public List<Scenic> findAllByCid(int pageNum, int pageSize, int c_id) {

		// TODO Auto-generated method stub
		String sql = "select "+columnName+" from scenic  where c_id=? limit ?,?  ";

		/*	sql += " limit ?,? ";*/
		int startNo = (pageNum - 1) * pageSize;
		List<Scenic> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Scenic>(Scenic.class), new Object[]{c_id, startNo, pageSize});// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public int queryScenicCount2(int c_id) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from scenic where c_id=? ";

		try {
			Long count = (Long) runner.query(sql, new ScalarHandler(), c_id);
			// 将long的类型转成int类型
			return count.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Scenic> getScenicList(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String sql = "select "+columnName+" from scenic  limit ?,? ";
		int startNo = (pageNum - 1) * pageSize;
		List<Scenic> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Scenic>(Scenic.class), new Object[]{startNo, pageSize});// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public int queryScenicCount3() {
		// TODO Auto-generated method stub
		String sql = "select count(*) from scenic  ";

		try {
			Long count = (Long) runner.query(sql, new ScalarHandler());
			// 将long的类型转成int类型
			return count.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


}
