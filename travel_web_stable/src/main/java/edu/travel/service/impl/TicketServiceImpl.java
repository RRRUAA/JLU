package edu.travel.service.impl;

import edu.travel.dao.TicketDao;
import edu.travel.dao.impl.TicketDaoImpl;
import edu.travel.entity.Ticket;
import edu.travel.service.TicketService;

import java.util.List;
import java.util.Map;

public class TicketServiceImpl implements TicketService {

	TicketDao dao = new TicketDaoImpl();


	@Override
	public Ticket findTicketById(String id) {
		// TODO Auto-generated method stub
		return dao.findTicketById(id);
	}

	@Override
	public void deleteTicket(String id) {
		// TODO Auto-generated method stub
		dao.deleteTicket(id);
	}

	@Override
	public void addTicket(Ticket Ticket) {
		// TODO Auto-generated method stub
		dao.addTicket(Ticket);
	}

	@Override
	public List<Ticket> getTicketPage(int pageNum, int pageSize, Map map) {
		// TODO Auto-generated method stub
		return dao.getTicketPage(pageNum, pageSize, map);
	}

	@Override
	public int queryTicketCount(Map map) {
		// TODO Auto-generated method stub
		return dao.queryTicketCount(map);
	}

	@Override
	public void updateTicket(Ticket Ticket) {
		// TODO Auto-generated method stub
		dao.updateTicket(Ticket);
	}

	@Override
	public List<Ticket> selectList(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return dao.selectList(pageNum, pageSize);
	}


}
