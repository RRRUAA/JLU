package edu.travel.dao;

import edu.travel.entity.Room;

import java.util.List;
import java.util.Map;

//酒店房间
public interface RoomDao {


	Room findRoomById(int id);

	void deleteRoom(int id);


	void addRoom(Room record);

	List<Room> getRoomPage(int pageNum, int pageSize, Map map);

	int queryRoomCount(Map map);

	void updateRoom(Room record);

	List<Room> selectList();

	void trySafeDel(int id);

	List<Room> selectRoomListBySid(Integer sid);
}
