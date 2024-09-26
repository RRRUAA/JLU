package edu.travel.dao.impl;

import edu.travel.dao.NewsDao;
import edu.travel.entity.News;
import edu.travel.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class NewsDaoImpl implements NewsDao {

	private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());
	private String columnName = "id,title,content,editor,fbsj";


	@Override
	public News findNewsById(String id) {
		try {// 返回查询的信息News
			return runner.query("select "+columnName+" from news where id=? ", new BeanHandler<News>(News.class),
					Integer.parseInt(id));
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void deleteNews(String id) {
		// TODO Auto-generated method stub
		Integer ids = Integer.parseInt(id);
		try {// 执行更改
			runner.update("update news set existStatus=0 where id=?", ids);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void addNews(News News) {
		// TODO Auto-generated method stub
		try {
			// 执行插入sql
			runner.update("insert into news(title,content,editor,fbsj) values (?,?,?,?)",
					News.getTitle(), News.getContent(), News.getEditor(), News.getFbsj());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<News> getNewsPage(int pageNum, int pageSize, Map map) {
		String sql = "select "+columnName+" from news where 1 = 1 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and title like  '%" + map.get("key").toString() + "%'";
		}
		sql += " limit ?,? ";
		int startNo = (pageNum - 1) * pageSize;
		List<News> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<News>(News.class), new Object[]{startNo, pageSize});// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public int queryNewsCount(Map map) {
		String sql = "select count(*) from news where 1 = 1 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and title like  '%" + map.get("key").toString() + "%'";
		}
		try {
			Long count = (Long) runner.query(sql, new ScalarHandler());
			// 将long的类型转成int类型
			return count.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateNews(News News) {
		// TODO Auto-generated method stub
		try {// 执行更改
			runner.update("update news set title=?,content=?,editor=? where id=?",
					News.getTitle(), News.getContent(), News.getEditor(), News.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public List<News> selectAll() {
		String sql = "select "+columnName+" from news order by id desc";
		List<News> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<News>(News.class));// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void trySafeDel(int id) {

	}


}
