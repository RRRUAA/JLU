package edu.travel.service;

import edu.travel.entity.Route;

import java.util.List;
import java.util.Map;

public interface RouteService {

	Route findRouteById(int id);

	void deleteRoute(int id);


	void addRoute(Route record);

	List<Route> getRoutePage(int pageNum, int pageSize, Map map);

	int queryRouteCount(Map map);

	void updateRoute(Route record);

	List<Route> selectList();

	List<Route> findMyRoute(int uid);


}
