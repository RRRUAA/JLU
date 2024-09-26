package edu.travel.dao.impl;

import edu.travel.dao.RoomDao;
import edu.travel.entity.Room;
import edu.travel.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RoomDaoImpl implements RoomDao {

	private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());
	private String columnName = "id,title,name,category,note,photo,price,address,phone";


	@Override
	public Room findRoomById(int id) {
		try {// 返回查询的信息Room
			return runner.query("select "+columnName+" from room where id=? ", new BeanHandler<Room>(Room.class),
					id);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void deleteRoom(int id) {
		// TODO Auto-generated method stub		
		try {// 执行更改
			runner.update("update room set existStatus = 0 where id=?", id);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


	@Override
	public void addRoom(Room record) {
		// TODO Auto-generated method stub
		try {
			// 执行插入sql
			runner.update("insert into room (title,name,category,note,photo,price,address,phone) values (?,?,?,?,?,?,?,?)",
					record.getTitle(), record.getName(), record.getCategory(), record.getNote(), record.getPhoto(), record.getPrice(), record.getAddress(), record.getPhone());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}


	}

	@Override
	public List<Room> getRoomPage(int pageNum, int pageSize, Map map) {
		String sql = "select "+columnName+" from room where 1 = 1 ";
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
		List<Room> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Room>(Room.class), new Object[]{startNo, pageSize});// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public int queryRoomCount(Map map) {
		String sql = "select count(*) from room where 1 = 1 ";
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
	public void updateRoom(Room record) {
		// TODO Auto-generated method stub
		try {// 执行更改
			runner.update("update room set title=?,name=?,category=?,note=?,photo=?,price=?,address=?,phone=? where id=?",
					record.getTitle(), record.getName(), record.getCategory(), record.getNote(), record.getPhoto(), record.getPrice(), record.getAddress(), record.getPhone(), record.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public List<Room> selectList() {
		String sql = "select "+columnName+" from room ";
		List<Room> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Room>(Room.class));// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void trySafeDel(int id) {

	}

	@Override
	public List<Room> selectRoomListBySid(Integer sid) {
		String sql = "select "+columnName+" from room where sid = ?";
		List<Room> list = null;
		try{
			list = runner.query(sql, new BeanListHandler<Room>(Room.class),sid);
			return list;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}


}
