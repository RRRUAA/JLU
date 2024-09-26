package edu.travel.dao;

import edu.travel.entity.Comments;
import edu.travel.entity.Statics;

import java.util.List;
import java.util.Map;

public interface CommentsDao {


	void deleteComments(String id);


	void addComments(Comments Comments);

	List<Comments> getCommentsPage(int pageNum, int pageSize, Map map);

	int queryCommentsCount(Map map);

	List<Comments> selectCommentsListBySid(int sid);

	List<Comments> findMyComment(String uid);

	Comments query(String oid);//查询是否有我的评论

	List<Statics> selectStaticsComments();

	void trySafeDel(int id);

}
