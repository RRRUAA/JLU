package edu.travel.service;

import edu.travel.entity.News;

import java.util.List;
import java.util.Map;

public interface NewsService {


	News findNewsById(String id);

	void deleteNews(String id);

	void addNews(News News);

	List<News> getNewsPage(int pageNum, int pageSize, Map map);

	int queryNewsCount(Map map);

	void updateNews(News News);

	List<News> selectAll();


}
