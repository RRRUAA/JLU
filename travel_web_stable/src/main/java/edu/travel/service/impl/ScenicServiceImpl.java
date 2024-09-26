package edu.travel.service.impl;

import edu.travel.dao.ScenicDao;
import edu.travel.dao.impl.ScenicDaoImpl;
import edu.travel.entity.Scenic;
import edu.travel.service.ScenicService;

import java.util.List;
import java.util.Map;

public class ScenicServiceImpl implements ScenicService {

	ScenicDao dao = new ScenicDaoImpl();


	@Override
	public Scenic findScenicById(String id) {
		// TODO Auto-generated method stub
		return dao.findScenicById(id);
	}

	@Override
	public void deleteScenic(String id) {
		// TODO Auto-generated method stub
		dao.deleteScenic(id);
	}

	@Override
	public void addScenic(Scenic Scenic) {
		// TODO Auto-generated method stub
		dao.addScenic(Scenic);
	}

	@Override
	public List<Scenic> getScenicPage(int pageNum, int pageSize, Map map) {
		// TODO Auto-generated method stub
		return dao.getScenicPage(pageNum, pageSize, map);
	}

	@Override
	public int queryScenicCount(Map map) {
		// TODO Auto-generated method stub
		return dao.queryScenicCount(map);
	}

	@Override
	public void updateScenic(Scenic Scenic) {
		// TODO Auto-generated method stub
		dao.updateScenic(Scenic);
	}

	@Override
	public List<Scenic> selectList() {
		// TODO Auto-generated method stub
		return dao.selectList();
	}

	@Override
	public List<Scenic> selectAll() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}

	@Override
	public List<Scenic> findAllBySid(List<Integer> ids) {
		// TODO Auto-generated method stub
		return dao.findAllBySid(ids);
	}

	@Override
	public List<Scenic> findAllByCid(int pageNum, int pageSize, int c_id) {
		// TODO Auto-generated method stub
		return dao.findAllByCid(pageNum, pageSize, c_id);
	}

	@Override
	public int queryScenicCountByCid(int c_id) {
		// TODO Auto-generated method stub
		return dao.queryScenicCount2(c_id);
	}

	@Override
	public List<Scenic> getScenicList(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return dao.getScenicList(pageNum, pageSize);
	}

	@Override
	public int queryScenicAllCount() {
		// TODO Auto-generated method stub
		return dao.queryScenicCount3();
	}


}
