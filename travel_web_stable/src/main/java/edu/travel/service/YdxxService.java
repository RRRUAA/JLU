package edu.travel.service;

import edu.travel.entity.Ydxx;

import java.util.List;
import java.util.Map;

public interface YdxxService {

	Ydxx findYdxxById(int id);

	void deleteYdxx(int id);


	void addYdxx(Ydxx record);

	List<Ydxx> getYdxxPage(int pageNum, int pageSize, Map map);

	int queryYdxxCount(Map map);


	List<Ydxx> selectList();

	List<Ydxx> findMyYdxx(int uid);

}
