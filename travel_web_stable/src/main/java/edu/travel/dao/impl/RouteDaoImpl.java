package edu.travel.dao.impl;

import edu.travel.dao.RouteDao;
import edu.travel.entity.Route;
import edu.travel.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class RouteDaoImpl implements RouteDao {

	private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());
	private String columnName="id,route_no,name,u_id,note,start_spot,end_spot,create_time";


	@Override
	public Route findRouteById(int id) {
		try {// 返回查询的信息Route
			return runner.query("select "+columnName+" from route where id=? ", new BeanHandler<Route>(Route.class),
					id);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void deleteRoute(int id) {
		// TODO Auto-generated method stub		
		try {// 执行更改
			runner.update("update route set existStatus = 0 where id=?", id);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void addRoute(Route record) {
		// TODO Auto-generated method stub
		try {
			// 执行插入sql
			runner.update("insert into route(route_no,name,u_id,note,start_spot,end_spot,create_time) values (?,?,?,?,?,?,now())",
					record.getRoute_no(), record.getName(), record.getU_id(), record.getNote(), record.getStart_spot(), record.getEnd_spot());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}


	}

	@Override
	public List<Route> getRoutePage(int pageNum, int pageSize, Map map) {
		String sql = "select "+columnName+" from route where 1 = 1 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and name like  '%" + map.get("key").toString() + "%'";
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
		List<Route> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Route>(Route.class), new Object[]{startNo, pageSize});// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public int queryRouteCount(Map map) {
		String sql = "select count(*) from route where 1 = 1 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and name like  '%" + map.get("key").toString() + "%'";
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
	public void updateRoute(Route record) {
		// TODO Auto-generated method stub
		try {// 执行更改
			runner.update("update route set route_no=?,name=?,note=?,start_spot=?,end_spot=? where id=?",
					record.getRoute_no(), record.getName(), record.getNote(), record.getStart_spot(), record.getEnd_spot(), record.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public List<Route> selectList() {
		String sql = "select "+columnName+" from route ";
		List<Route> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Route>(Route.class));// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void trySafeDel(int id) {

	}

	@Override
	public List<Route> findRouteByUid(int uid) {
		// TODO Auto-generated method stub
		String sql = "select * from route where u_id=? ";
		List<Route> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Route>(Route.class), uid);// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


}
