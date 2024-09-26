package edu.travel.service;

import edu.travel.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {


	Category findCategoryById(String id);

	void deleteCategory(String id);

	void addCategory(Category record);

	List<Category> getCategoryPage(int pageNum, int pageSize, Map map);

	int queryCategoryCount(Map map);

	void updateCategory(Category record);

	List<Category> selectAll();


}
