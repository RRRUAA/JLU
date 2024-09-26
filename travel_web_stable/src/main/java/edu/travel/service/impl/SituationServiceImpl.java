package edu.travel.service.impl;

import edu.travel.dao.SituationDao;
import edu.travel.dao.impl.SituationDaoImpl;
import edu.travel.entity.Situation;
import edu.travel.service.SituationService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SituationServiceImpl implements SituationService {
	private SituationDao dao = new SituationDaoImpl();
	@Override
	public List<Situation> getSituationPage(int pageNum, int pageSize, Map<String, Object> map) {
		return dao.getSituationPage( pageNum, pageSize, map);
	}

	@Override
	public int querySituationCount(Map<String, Object> map) {
		return dao.querySituationCount( map);
	}

	@Override
	public Situation findSituationById(String situationId) {
		return dao.findSituationById(situationId);
	}
}
