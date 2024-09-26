package edu.travel.service;

import edu.travel.entity.Room;

import java.util.List;
import java.util.Map;

public interface RoomService {

	Room findRoomById(int id);

	void deleteRoom(int id);


	void addRoom(Room record);

	List<Room> getRoomPage(int pageNum, int pageSize, Map map);

	int queryRoomCount(Map map);

	void updateRoom(Room record);

	List<Room> selectList();


	List<Room> selectRoomListBySid(Integer sid);
}
