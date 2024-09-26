package edu.travel.service;

import edu.travel.entity.Scenic;

import java.util.List;
import java.util.Map;

public interface ScenicService {


	Scenic findScenicById(String id);

	void deleteScenic(String id);

	void addScenic(Scenic Scenic);

	List<Scenic> getScenicPage(int pageNum, int pageSize, Map map);

	int queryScenicCount(Map map);

	void updateScenic(Scenic Scenic);

	List<Scenic> selectList();

	List<Scenic> selectAll();

	List<Scenic> findAllBySid(List<Integer> ids);

	List<Scenic> findAllByCid(int pageNum, int pageSize, int c_id);

	int queryScenicCountByCid(int c_id);

	List<Scenic> getScenicList(int pageNum, int pageSize);

	int queryScenicAllCount();
}
