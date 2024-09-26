package edu.travel.dao.impl;

import edu.travel.dao.SituationDao;
import edu.travel.entity.Room;
import edu.travel.entity.Situation;
import edu.travel.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SituationDaoImpl implements SituationDao {
	private QueryRunner runner = new QueryRunner(C3p0Utils.getDs());
	private String columnName = "id,sid,queuenum";
	@Override
	public List<Situation> getSituationPage(int pageNum, int pageSize, Map<String, Object> map) {
		String sql = "select "+columnName+" from situation where 1 = 1 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and name like  '%" + map.get("key").toString() + "%'";
		}
		if(map.get("price1") !=null && !map.get("price1").toString().equals("")){
	            sql+= " and queuenum >= " +map.get("price1") ;
	    }
	    if(map.get("price2") !=null && !map.get("price2").toString().equals("")){
	            sql+= " and queuenum <= " +map.get("price2") ;
	    }
		if (map.get("orderBy") != null && map.get("orderBy").toString().equals("1")) {
			sql += " order by queuenum desc ";
		}
		/*if (map.get("orderBy") != null && map.get("orderBy").toString().equals("2")) {
			sql += " order by create_date desc ";
		}*/
		sql += " limit ?,? ";
		int startNo = (pageNum - 1) * pageSize;
		List<Situation> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Situation>(Situation.class), new Object[]{startNo, pageSize});// 添加实体类的适配器进行类的反射
			return list;
		} catch (SQLException e) {// 捕获异常
			throw new RuntimeException(e);// 抛出运行异常
		}
	}

	@Override
	public int querySituationCount(Map<String, Object> map) {
		String sql = "select count(*) from situation where 1 = 1 ";
		if (map.get("key") != null && !map.get("key").toString().equals("")) {
			sql += " and name like  '%" + map.get("key").toString() + "%'";
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
	public Situation findSituationById(String situationId) {
		String sql = "select * from situation where id = ?";
		try {
			return runner.query(sql, new BeanHandler<Situation>(Situation.class), situationId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
