package edu.travel.service.impl;

import edu.travel.dao.CommentsDao;
import edu.travel.dao.impl.CommentsDaoImpl;
import edu.travel.entity.Comments;
import edu.travel.entity.Statics;
import edu.travel.service.CommentsService;

import java.util.List;
import java.util.Map;

public class CommentsServiceImpl implements CommentsService {

	CommentsDao dao = new CommentsDaoImpl();


	@Override
	public void deleteComments(String id) {
		// TODO Auto-generated method stub
		dao.deleteComments(id);
	}

	@Override
	public void addComments(Comments Comments) {
		// TODO Auto-generated method stub
		dao.addComments(Comments);
	}

	@Override
	public List<Comments> getCommentsPage(int pageNum, int pageSize, Map map) {
		// TODO Auto-generated method stub
		return dao.getCommentsPage(pageNum, pageSize, map);
	}

	@Override
	public int queryCommentsCount(Map map) {
		// TODO Auto-generated method stub
		return dao.queryCommentsCount(map);
	}

	@Override
	public List<Comments> selectCommentsListBySid(int sid) {
		// TODO Auto-generated method stub
		return dao.selectCommentsListBySid(sid);
	}

	@Override
	public List<Statics> selectStaticsComments() {
		// TODO Auto-generated method stub
		return dao.selectStaticsComments();
	}

	@Override
	public List<Comments> findMyComment(String uid) {
		// TODO Auto-generated method stub
		return dao.findMyComment(uid);
	}

	@Override
	public boolean existsComment(String oid) {
		// TODO Auto-generated method stub
		if (dao.query(oid) == null) {
			return false;//表示不可以用
		}
		return true;//表示可以用
	}


}
