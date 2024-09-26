package edu.travel.dao;

import edu.travel.entity.Route;

import java.util.List;
import java.util.Map;

//酒店房间
public interface RouteDao {


	Route findRouteById(int id);

	void deleteRoute(int id);


	void addRoute(Route record);

	List<Route> getRoutePage(int pageNum, int pageSize, Map map);

	List<Route> findRouteByUid(int uid);

	int queryRouteCount(Map map);

	void updateRoute(Route record);

	List<Route> selectList();

	void trySafeDel(int id);

}
