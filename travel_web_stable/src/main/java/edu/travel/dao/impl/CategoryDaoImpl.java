package edu.travel.dao.impl;

import edu.travel.dao.CategoryDao;
import edu.travel.entity.Category;
import edu.travel.entity.Scenic;
import edu.travel.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CategoryDaoImpl implements CategoryDao {

	private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());
	private String columnName = "id, cname";

	public void trySafeDel(int id){
		String sql = "select "+columnName+" from category where id = ? and existStatus = 0";
		List<Category> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Category>(Category.class), id);
			if(list == null || list.size() == 0){
				return;
			}
			sql = "select id,names,detail,photo,create_date,c_id from scenic where c_id = ?";
			List<Scenic> scenicList = runner.query(sql, new BeanListHandler<Scenic>(Scenic.class), id);
			if(scenicList != null && !scenicList.isEmpty()){
				return;
			}
			sql = "delete from category where id = ?";
			runner.update(sql, id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	@Override
	public Category findCategoryById(String id) {
		try {// 返回查询的信息Category
			return runner.query("select " + columnName + " from category where id=?", new BeanHandler<Category>(Category.class),
					Integer.parseInt(id));
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void deleteCategory(String id) {
		// TODO Auto-generated method stub
		Integer ids = Integer.parseInt(id);
		try {// 执行更改
			runner.update("update category set existStatus = 0 where id=?", ids);
			trySafeDel(ids);
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public void addCategory(Category record) {
		// TODO Auto-generated method stub
		try {
			// 执行插入sql
			runner.update("insert into category(cname) values (?)",
					record.getCname());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<Category> getCategoryPage(int pageNum, int pageSize, Map map) {
		String sql = "select " + columnName + " from category where existStatus>0 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and cname like '%" + map.get("key").toString() + "%'";
		}
		sql += " limit ?,? ";
		int startNo = (pageNum - 1) * pageSize;
		List<Category> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Category>(Category.class), new Object[]{startNo, pageSize});// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public int queryCategoryCount(Map map) {
		String sql = "select count(*) from category where existStatus>0 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and cname like  '%" + map.get("key").toString() + "%'";
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
	public void updateCategory(Category record) {
		// TODO Auto-generated method stub
		try {// 执行更改
			runner.update("update category set cname=? where id=?",
					record.getCname(), record.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public List<Category> selectAll() {
		String sql = "select " + columnName + " from category where existStatus>0 ";
		List<Category> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Category>(Category.class));// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}


}
