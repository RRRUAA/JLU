package edu.travel.service.impl;

import edu.travel.dao.CategoryDao;
import edu.travel.dao.impl.CategoryDaoImpl;
import edu.travel.entity.Category;
import edu.travel.service.CategoryService;

import java.util.List;
import java.util.Map;

public class CategoryServiceImpl implements CategoryService {

	CategoryDao dao = new CategoryDaoImpl();


	@Override
	public Category findCategoryById(String id) {
		// TODO Auto-generated method stub
		return dao.findCategoryById(id);
	}

	@Override
	public void deleteCategory(String id) {
		// TODO Auto-generated method stub
		dao.deleteCategory(id);
	}

	@Override
	public void addCategory(Category Category) {
		// TODO Auto-generated method stub
		dao.addCategory(Category);
	}

	@Override
	public List<Category> getCategoryPage(int pageNum, int pageSize, Map map) {
		// TODO Auto-generated method stub
		return dao.getCategoryPage(pageNum, pageSize, map);
	}

	@Override
	public int queryCategoryCount(Map map) {
		// TODO Auto-generated method stub
		return dao.queryCategoryCount(map);
	}

	@Override
	public void updateCategory(Category Category) {
		// TODO Auto-generated method stub
		dao.updateCategory(Category);
	}

	@Override
	public List<Category> selectAll() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}


}
