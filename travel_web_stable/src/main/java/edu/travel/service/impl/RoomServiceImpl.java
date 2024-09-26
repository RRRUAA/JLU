package edu.travel.service.impl;

import edu.travel.dao.RoomDao;
import edu.travel.dao.impl.RoomDaoImpl;
import edu.travel.entity.Room;
import edu.travel.service.RoomService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RoomServiceImpl implements RoomService {

	RoomDao dao = new RoomDaoImpl();


	@Override
	public Room findRoomById(int id) {
		// TODO Auto-generated method stub
		return dao.findRoomById(id);
	}

	@Override
	public void deleteRoom(int id) {
		// TODO Auto-generated method stub
		dao.deleteRoom(id);
	}

	@Override
	public void addRoom(Room room) {
		// TODO Auto-generated method stub
		dao.addRoom(room);
	}

	@Override
	public List<Room> getRoomPage(int pageNum, int pageSize, Map map) {
		// TODO Auto-generated method stub
		return dao.getRoomPage(pageNum, pageSize, map);
	}

	@Override
	public int queryRoomCount(Map map) {
		// TODO Auto-generated method stub
		return dao.queryRoomCount(map);
	}

	@Override
	public void updateRoom(Room room) {
		// TODO Auto-generated method stub
		dao.updateRoom(room);
	}

	@Override
	public List<Room> selectList() {
		// TODO Auto-generated method stub
		return dao.selectList();
	}

	@Override
	public List<Room> selectRoomListBySid(Integer sid) {
		return dao.selectRoomListBySid(sid);
	}


}
