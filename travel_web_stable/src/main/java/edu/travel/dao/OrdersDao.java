package edu.travel.dao;

import edu.travel.entity.Orders;
import edu.travel.entity.Statics;

import java.util.List;
import java.util.Map;

public interface OrdersDao {


	Orders findOrdersById(String id);

	void deleteOrders(String id);


	void addOrders(Orders Orders);

	void update(int id);

	List<Orders> getOrdersPage(int pageNum, int pageSize, Map map);

	int queryOrdersCount(Map map);

	List<Orders> selectOrdersByUserId(int i);

	List<Statics> selectStaticsOrders();


	Orders findOrdersByOrderId(String b);

	void removeValid(String s);

	void trySafeDel(int id);
}
