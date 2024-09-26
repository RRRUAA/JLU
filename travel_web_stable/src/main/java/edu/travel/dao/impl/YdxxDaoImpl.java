package edu.travel.dao.impl;

import edu.travel.dao.YdxxDao;
import edu.travel.entity.Ydxx;
import edu.travel.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class YdxxDaoImpl implements YdxxDao {

	private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());
	private String columnName = "id,ydbh,uid,days,room_id,total_price,status,note,start_date,end_date,create_time";


	@Override
	public Ydxx findYdxxById(int id) {
		try {// 返回查询的信息Ydxx
			return runner.query("select "+columnName+" from ydxx where id=? ", new BeanHandler<Ydxx>(Ydxx.class),
					id);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void deleteYdxx(int id) {
		// TODO Auto-generated method stub		
		try {// 执行更改
			runner.update("update ydxx set existStatus=0 where id=?", id);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


	@Override
	public void addYdxx(Ydxx record) {
		// TODO Auto-generated method stub
		try {
			// 执行插入sql
			runner.update("insert into ydxx(ydbh,uid,days,room_id,total_price,status,note,start_date,end_date,create_time) values (?,?,?,?,?,?,?,?,?,now())",
					record.getYdbh(), record.getUid(), record.getDays(), record.getRoom_id(), record.getTotal_price(), record.getStatus(), record.getNote(), record.getStart_date(), record.getEnd_date());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}


	}

	@Override
	public List<Ydxx> getYdxxPage(int pageNum, int pageSize, Map map) {
		String sql = "select "+columnName+" from ydxx where 1 = 1 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and ydbh like  '%" + map.get("key").toString() + "%'";
		}
		/*if(map.get("price1") !=null && !map.get("price1").toString().equals("")){
	            sql+= " and price >= " +map.get("price1") ;
	    }
	    if(map.get("price2") !=null && !map.get("price2").toString().equals("")){
	            sql+= " and price <= " +map.get("price2") ;
	    }
		if (map.get("orderBy") != null && map.get("orderBy").toString().equals("1")) {
			sql += " order by price desc ";
		}
		if (map.get("orderBy") != null && map.get("orderBy").toString().equals("2")) {
			sql += " order by create_date desc ";
		}*/
		sql += " limit ?,? ";
		int startNo = (pageNum - 1) * pageSize;
		List<Ydxx> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Ydxx>(Ydxx.class), new Object[]{startNo, pageSize});// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public int queryYdxxCount(Map map) {
		String sql = "select count(*) from ydxx where 1 = 1 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and ydbh like  '%" + map.get("key").toString() + "%'";
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
	public List<Ydxx> selectList() {
		String sql = "select "+columnName+" from ydxx ";
		List<Ydxx> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Ydxx>(Ydxx.class));// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void trySafeDel(int id) {

	}

	@Override
	public List<Ydxx> findYdxxByUid(int uid) {
		// TODO Auto-generated method stub
		String sql = "select "+columnName+" from ydxx where uid=? order   by  create_time desc ";
		List<Ydxx> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Ydxx>(Ydxx.class), uid);// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


}
