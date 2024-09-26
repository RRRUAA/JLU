package edu.travel.service.impl;

import edu.travel.dao.RouteDao;
import edu.travel.dao.impl.RouteDaoImpl;
import edu.travel.entity.Route;
import edu.travel.service.RouteService;

import java.util.List;
import java.util.Map;

public class RouteServiceImpl implements RouteService {

	RouteDao dao = new RouteDaoImpl();


	@Override
	public Route findRouteById(int id) {
		// TODO Auto-generated method stub
		return dao.findRouteById(id);
	}

	@Override
	public void deleteRoute(int id) {
		// TODO Auto-generated method stub
		dao.deleteRoute(id);
	}

	@Override
	public void addRoute(Route record) {
		// TODO Auto-generated method stub
		dao.addRoute(record);
	}

	@Override
	public List<Route> getRoutePage(int pageNum, int pageSize, Map map) {
		// TODO Auto-generated method stub
		return dao.getRoutePage(pageNum, pageSize, map);
	}

	@Override
	public int queryRouteCount(Map map) {
		// TODO Auto-generated method stub
		return dao.queryRouteCount(map);
	}

	@Override
	public void updateRoute(Route record) {
		// TODO Auto-generated method stub
		dao.updateRoute(record);
	}

	@Override
	public List<Route> selectList() {
		// TODO Auto-generated method stub
		return dao.selectList();
	}

	@Override
	public List<Route> findMyRoute(int uid) {
		// TODO Auto-generated method stub
		return dao.findRouteByUid(uid);
	}


}
