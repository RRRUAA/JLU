package edu.travel.service.impl;

import edu.travel.dao.YdxxDao;
import edu.travel.dao.impl.YdxxDaoImpl;
import edu.travel.entity.Ydxx;
import edu.travel.service.YdxxService;

import java.util.List;
import java.util.Map;

public class YdxxServiceImpl implements YdxxService {

	YdxxDao dao = new YdxxDaoImpl();


	@Override
	public Ydxx findYdxxById(int id) {
		// TODO Auto-generated method stub
		return dao.findYdxxById(id);
	}

	@Override
	public void deleteYdxx(int id) {
		// TODO Auto-generated method stub
		dao.deleteYdxx(id);
	}

	@Override
	public void addYdxx(Ydxx record) {
		// TODO Auto-generated method stub
		dao.addYdxx(record);
	}

	@Override
	public List<Ydxx> getYdxxPage(int pageNum, int pageSize, Map map) {
		// TODO Auto-generated method stub
		return dao.getYdxxPage(pageNum, pageSize, map);
	}

	@Override
	public int queryYdxxCount(Map map) {
		// TODO Auto-generated method stub
		return dao.queryYdxxCount(map);
	}


	@Override
	public List<Ydxx> selectList() {
		// TODO Auto-generated method stub
		return dao.selectList();
	}

	@Override
	public List<Ydxx> findMyYdxx(int uid) {
		// TODO Auto-generated method stub
		return dao.findYdxxByUid(uid);
	}


}
