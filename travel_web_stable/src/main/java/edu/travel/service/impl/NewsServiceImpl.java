package edu.travel.service.impl;

import edu.travel.dao.NewsDao;
import edu.travel.dao.impl.NewsDaoImpl;
import edu.travel.entity.News;
import edu.travel.service.NewsService;

import java.util.List;
import java.util.Map;

public class NewsServiceImpl implements NewsService {

	NewsDao dao = new NewsDaoImpl();


	@Override
	public News findNewsById(String id) {
		// TODO Auto-generated method stub
		return dao.findNewsById(id);
	}

	@Override
	public void deleteNews(String id) {
		// TODO Auto-generated method stub
		dao.deleteNews(id);
	}

	@Override
	public void addNews(News News) {
		// TODO Auto-generated method stub
		dao.addNews(News);
	}

	@Override
	public List<News> getNewsPage(int pageNum, int pageSize, Map map) {
		// TODO Auto-generated method stub
		return dao.getNewsPage(pageNum, pageSize, map);
	}

	@Override
	public int queryNewsCount(Map map) {
		// TODO Auto-generated method stub
		return dao.queryNewsCount(map);
	}

	@Override
	public void updateNews(News News) {
		// TODO Auto-generated method stub
		dao.updateNews(News);
	}

	@Override
	public List<News> selectAll() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}


}
