package edu.travel.dao.impl;

import edu.travel.dao.ScenicDao;
import edu.travel.dao.TicketDao;
import edu.travel.entity.Category;
import edu.travel.entity.Orders;
import edu.travel.entity.Scenic;
import edu.travel.entity.Ticket;
import edu.travel.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TicketDaoImpl implements TicketDao {

	private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());
	private String columnName = "id,sid,level,price,sotock,create_date";


	@Override
	public Ticket findTicketById(String id) {
		try {// 返回查询的信息Ticket
			return runner.query("select "+columnName+" from ticket where id=? ", new BeanHandler<Ticket>(Ticket.class),
					id);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void deleteTicket(String id) {
		// TODO Auto-generated method stub
		Integer ids = Integer.parseInt(id);
		try {// 执行更改
			runner.update("update ticket set existStatus=0 where id=?", ids);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void addTicket(Ticket Ticket) {
		// TODO Auto-generated method stub
		try {
			// 执行插入sql
			runner.update("insert into ticket(sid,level,price,sotock,create_date) values (?,?,?,?,?)",
					Ticket.getSid(), Ticket.getLevel(), Ticket.getPrice(), Ticket.getSotock(), Ticket.getCreate_date());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}


	}

	@Override
	public List<Ticket> getTicketPage(int pageNum, int pageSize, Map map) {
		String sql = "select "+columnName+" from ticket where 1 = 1 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and level like  '%" + map.get("key").toString() + "%'";
		}
		if (map.get("price1") != null && !map.get("price1").toString().equals("")) {
			sql += " and price >= " + map.get("price1");
		}
		if (map.get("price2") != null && !map.get("price2").toString().equals("")) {
			sql += " and price <= " + map.get("price2");
		}
		if (map.get("orderBy") != null && map.get("orderBy").toString().equals("1")) {
			sql += " order by price desc ";
		}
		if (map.get("orderBy") != null && map.get("orderBy").toString().equals("2")) {
			sql += " order by create_date desc ";
		}
		sql += " limit ?,? ";
		int startNo = (pageNum - 1) * pageSize;
		List<Ticket> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Ticket>(Ticket.class), new Object[]{startNo, pageSize});// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public List<Ticket> selectList(int pageNum, int pageSize) {
		String sql = "select "+columnName+" from ticket limit ?,?  ";


		int startNo = (pageNum - 1) * pageSize;
		List<Ticket> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Ticket>(Ticket.class), new Object[]{startNo, pageSize});// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void trySafeDel(int id) {
		String sql = "select "+columnName+" from ticket where id = ? and existStatus = 0";
		List<Ticket> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Ticket>(Ticket.class), id);
			if(list == null || list.size() == 0){
				return;
			}
			sql = "select id,ordernum,quantity,total_price,pay_way,create_time,tid,uid,uname,status from orders where tid = ?";
			List<Orders> ordersList = runner.query(sql, new BeanListHandler<Orders>(Orders.class), id);
			if(ordersList != null && !ordersList.isEmpty()){
				return;
			}
			sql = "delete from ticket where id = ?";
			runner.update(sql, id);
			ScenicDao scenicDao = new ScenicDaoImpl();
			scenicDao.trySafeDel(list.get(0).getSid());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int queryTicketCount(Map map) {
		String sql = "select count(*) from ticket where 1 = 1 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and level like  '%" + map.get("key").toString() + "%'";
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
	public void updateTicket(Ticket Ticket) {
		// TODO Auto-generated method stub
		try {// 执行更改
			runner.update("update ticket set sid=?,level=?,price=?,sotock=? where id=?",
					Ticket.getSid(), Ticket.getLevel(), Ticket.getPrice(), Ticket.getSotock(), Ticket.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


}
