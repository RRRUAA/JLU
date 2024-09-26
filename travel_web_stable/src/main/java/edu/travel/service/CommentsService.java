package edu.travel.service;

import edu.travel.entity.Comments;
import edu.travel.entity.Statics;

import java.util.List;
import java.util.Map;

//评论业务层
public interface CommentsService {


	void deleteComments(String id);

	void addComments(Comments Comments);

	List<Comments> getCommentsPage(int pageNum, int pageSize, Map map);

	int queryCommentsCount(Map map);

	List<Comments> selectCommentsListBySid(int sid);

	List<Statics> selectStaticsComments();

	List<Comments> findMyComment(String uid);

	boolean existsComment(String oid);//判断是否存在评论


}
