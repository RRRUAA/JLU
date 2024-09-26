package edu.travel.service.impl;

import edu.travel.dao.OrdersDao;
import edu.travel.dao.impl.OrdersDaoImpl;
import edu.travel.entity.Orders;
import edu.travel.entity.Statics;
import edu.travel.service.OrdersService;

import java.util.List;
import java.util.Map;

public class OrdersServiceImpl implements OrdersService {

	OrdersDao dao = new OrdersDaoImpl();


	@Override
	public Orders findOrdersById(String id) {
		// TODO Auto-generated method stub
		return dao.findOrdersById(id);
	}

	@Override
	public void deleteOrders(String id) {
		// TODO Auto-generated method stub
		dao.deleteOrders(id);
	}

	@Override
	public void addOrders(Orders Orders) {
		// TODO Auto-generated method stub
		dao.addOrders(Orders);
	}

	@Override
	public List<Orders> getOrdersPage(int pageNum, int pageSize, Map map) {
		// TODO Auto-generated method stub
		return dao.getOrdersPage(pageNum, pageSize, map);
	}

	@Override
	public int queryOrdersCount(Map map) {
		// TODO Auto-generated method stub
		return dao.queryOrdersCount(map);
	}

	@Override
	public List<Orders> selectOrdersByUserId(int i) {
		// TODO Auto-generated method stub
		return dao.selectOrdersByUserId(i);
	}

	@Override
	public List<Statics> selectStaticsOrders() {
		// TODO Auto-generated method stub
		return dao.selectStaticsOrders();
	}

	@Override
	public void updateOrdersStatus(int id) {
		dao.update(id);

	}


}
