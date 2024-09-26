package edu.travel.service;

import edu.travel.entity.Orders;
import edu.travel.entity.Statics;

import java.util.List;
import java.util.Map;

public interface OrdersService {


	Orders findOrdersById(String id);

	void deleteOrders(String id);

	void addOrders(Orders Orders);

	List<Orders> getOrdersPage(int pageNum, int pageSize, Map map);

	int queryOrdersCount(Map map);

	List<Orders> selectOrdersByUserId(int i);

	List<Statics> selectStaticsOrders();

	void updateOrdersStatus(int id);


}
