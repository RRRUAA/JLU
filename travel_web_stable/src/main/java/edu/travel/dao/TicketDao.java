package edu.travel.dao;

import edu.travel.entity.Ticket;

import java.util.List;
import java.util.Map;

public interface TicketDao {


	Ticket findTicketById(String id);

	void deleteTicket(String id);


	void addTicket(Ticket Ticket);

	List<Ticket> getTicketPage(int pageNum, int pageSize, Map map);

	int queryTicketCount(Map map);

	void updateTicket(Ticket Ticket);

	List<Ticket> selectList(int pageNum, int pageSize);

	void trySafeDel(int id);

}
