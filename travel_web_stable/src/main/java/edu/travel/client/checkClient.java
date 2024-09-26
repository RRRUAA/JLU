package edu.travel.client;

import edu.travel.dao.OrdersDao;
import edu.travel.dao.ScenicDao;
import edu.travel.dao.impl.OrdersDaoImpl;
import edu.travel.dao.impl.ScenicDaoImpl;
import edu.travel.entity.Orders;

import java.util.Scanner;

public class checkClient {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n请输入景点编号。\n景点编号：");
		while(true){
			int a = sc.nextInt();
			if(checkScenic(a)){
				break;
			}
			System.out.println("\n不存在该景点，请重新输入！\n景点编号：");
		}
		System.out.println("\n请输入订单编号。\n订单编号：");
		while(true){
			String b = sc.next();
			if(checkOrder(b)){
				break;
			}
			System.out.println("请重新输入订单编号！\n订单编号：");
		}
	}

	private static boolean checkOrder(String b) {
		OrdersDao dao = new OrdersDaoImpl();
		Orders orders = dao.findOrdersByOrderId(b);
		if(orders!=null)
			return true;
		else {
			dao.removeValid(String.valueOf(orders.getId()));
			return false;
		}
	}

	private static boolean checkScenic(int a) {
		ScenicDao dao = new ScenicDaoImpl();
		if(dao.findScenicById(String.valueOf(a))!=null)
			return true;
		else
			return false;
	}
}
