package edu.travel.dao.impl;

import edu.travel.dao.CommentsDao;
import edu.travel.dao.OrdersDao;
import edu.travel.entity.Comments;
import edu.travel.entity.Statics;
import edu.travel.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CommentsDaoImpl implements CommentsDao {

	private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());
	private String columnName = "id,uid,sid,content,create_time,uphoto,uname,oid";


	@Override
	public void deleteComments(String id) {
		// TODO Auto-generated method stub
		Integer ids = Integer.parseInt(id);
		try {// 执行更改
			runner.update("update comments set existStatus = 0 where id=?", ids);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void addComments(Comments r) {
		// TODO Auto-generated method stub
		try {
			// 执行插入sql
			runner.update("insert into comments(uid,sid,content,create_time,uphoto,uname,oid) values (?,?,?,?,?,?,?)",
					r.getUid(), r.getSid(), r.getContent(), r.getCreate_time(), r.getUphoto(), r.getUname(), r.getOid());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}


	}

	@Override
	public List<Comments> getCommentsPage(int pageNum, int pageSize, Map map) {
		String sql = "select "+columnName+" from comments where 1 = 1 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and uname like  '%" + map.get("key").toString() + "%'";
		}
		sql += " limit ?,? ";
		int startNo = (pageNum - 1) * pageSize;
		List<Comments> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Comments>(Comments.class), new Object[]{startNo, pageSize});// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public int queryCommentsCount(Map map) {
		String sql = "select count(*) from comments where 1 = 1 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and uname like  '%" + map.get("key").toString() + "%'";
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
	public List<Comments> selectCommentsListBySid(int sid) {
		String sql = "select "+columnName+" from comments where sid =  " + sid;
		List<Comments> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Comments>(Comments.class));// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public List<Statics> selectStaticsComments() {
		// TODO Auto-generated method stub
		String sql = "select count(1) as counts , date_format(create_time ,'%Y-%m-%d' )  as names from Comments  GROUP BY names ";
		List<Statics> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Statics>(Statics.class));//添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {//捕获异常
			throw new RuntimeException(e);//抛出运行异常
		}
	}

	@Override
	public void trySafeDel(int id) {
		String sql = "delete from comments where id = ?" ;
		try {
			Comments comments = runner.query("select "+columnName+" from comments where id = ?",new BeanHandler<Comments>(Comments.class),id);
			runner.update(sql,id);
			OrdersDao ordersDao = new OrdersDaoImpl();
			ordersDao.trySafeDel(comments.getOid());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Comments> findMyComment(String uid) {
		// TODO Auto-generated method stub
		String sql = "select "+columnName+" from comments where uid=?";
		List<Comments> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Comments>(Comments.class), uid);// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public Comments query(String oid) {
		try {// 返回查询的信息Scenic
			return runner.query("select "+columnName+" from comments where oid=? ", new BeanHandler<Comments>(Comments.class),
					oid);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


}
