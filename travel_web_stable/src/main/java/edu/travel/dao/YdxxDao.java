package edu.travel.dao;

import edu.travel.entity.Ydxx;

import java.util.List;
import java.util.Map;

//酒店房间
public interface YdxxDao {


	Ydxx findYdxxById(int id);

	void deleteYdxx(int id);


	void addYdxx(Ydxx record);

	List<Ydxx> getYdxxPage(int pageNum, int pageSize, Map map);

	int queryYdxxCount(Map map);


	List<Ydxx> findYdxxByUid(int uid);

	List<Ydxx> selectList();

	void trySafeDel(int id);

}
