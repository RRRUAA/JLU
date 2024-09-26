package edu.travel.dao;

import edu.travel.entity.Scenic;

import java.util.List;
import java.util.Map;

public interface ScenicDao {


	Scenic findScenicById(String id);

	void deleteScenic(String id);


	void addScenic(Scenic Scenic);

	List<Scenic> getScenicPage(int pageNum, int pageSize, Map map);

	int queryScenicCount(Map map);

	int queryScenicCount2(int c_id);

	int queryScenicCount3();

	void updateScenic(Scenic Scenic);

	List<Scenic> selectList();

	List<Scenic> selectAll();

	List<Scenic> findAllBySid(List<Integer> ids);

	List<Scenic> getScenicList(int pageNum, int pageSize);

	List<Scenic> findAllByCid(int pageNum, int pageSize, int c_id);

	void trySafeDel(int id);

}
