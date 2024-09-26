package edu.travel.dao.impl;

import edu.travel.dao.OrdersDao;
import edu.travel.dao.TicketDao;
import edu.travel.dao.UserDao;
import edu.travel.entity.*;
import edu.travel.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrdersDaoImpl implements OrdersDao {

	private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());
	private String columnName = "id,ordernum,quantity,total_price,pay_way,create_time,tid,uid,uname,status";

	@Override
	public Orders findOrdersById(String id) {
		try {// 返回查询的信息Orders
			return runner.query("select "+columnName+" from orders where id=? ", new BeanHandler<Orders>(Orders.class),
					Integer.parseInt(id));
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void deleteOrders(String id) {
		// TODO Auto-generated method stub
		Integer ids = Integer.parseInt(id);
		try {// 执行更改
			runner.update("update orders set existStatus=0 where id=?", ids);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void addOrders(Orders Orders) {
		// TODO Auto-generated method stub
		try {
			// 执行插入sql
			runner.update("insert into orders(ordernum,quantity,total_price,pay_way,create_time,tid,uid,uname,status) values (?,?,?,?,?,?,?,?,0)",
					Orders.getOrdernum(), Orders.getQuantity(), Orders.getTotal_price(), Orders.getPay_way(), Orders.getCreate_time()
					, Orders.getTid(), Orders.getUid(), Orders.getUname());

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}


	}

	@Override
	public List<Orders> getOrdersPage(int pageNum, int pageSize, Map map) {
		String sql = "select "+columnName+" from orders where 1 = 1 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and uname like  '%" + map.get("key").toString() + "%'";
		}
		sql += " limit ?,? ";
		int startNo = (pageNum - 1) * pageSize;
		List<Orders> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Orders>(Orders.class), new Object[]{startNo, pageSize});// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public int queryOrdersCount(Map map) {
		String sql = "select count(*) from orders where 1 = 1 ";
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
	public List<Orders> selectOrdersByUserId(int i) {
		String sql = "select "+columnName+" from orders where uid = " + i;
		List<Orders> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Orders>(Orders.class));// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public List<Statics> selectStaticsOrders() {
		String sql = "select sum(quantity) as counts , date_format(create_time ,'%Y-%m-%d' )  as names from orders  GROUP BY names ";
		List<Statics> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Statics>(Statics.class));//添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {//捕获异常
			throw new RuntimeException(e);//抛出运行异常
		}
	}

	@Override
	public Orders findOrdersByOrderId(String oid) {
		try {// 返回查询的信息Orders
			return runner.query("select "+columnName+" from orders where ordernum like ? and existStatus>0 and isValid >0", new BeanHandler<Orders>(Orders.class),
					oid);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void removeValid(String id) {
		try {
			runner.update("update orders set isValid=0 where id=?",Integer.parseInt(id));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void trySafeDel(int id) {
		String sql = "select "+columnName+" from orders where id = ? and existStatus = 0";
		List<Orders> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Orders>(Orders.class), id);
			if(list == null || list.size() == 0){
				return;
			}
			sql = "select id,uid,sid,content,create_time,uphoto,uname,oid from comments where oid = ?";
			List<Comments> commentsList = runner.query(sql, new BeanListHandler<Comments>(Comments.class), id);
			if(commentsList != null && !commentsList.isEmpty()){
				return;
			}
			sql = "delete from orders where id = ?";
			runner.update(sql, id);
			TicketDao ticketDao = new TicketDaoImpl();
			ticketDao.trySafeDel(list.get(0).getTid());
			UserDao userDao = new UserDaoImpl();
			userDao.trySafeDel(list.get(0).getUid());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(int id) {
		// TODO Auto-generated method stub
		try {// 执行更改
			runner.update("update orders set status=1 where id=?", id);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}

	}


}
