package edu.travel.dao;

import edu.travel.entity.Situation;

import java.util.List;
import java.util.Map;

public interface SituationDao {

	List<Situation> getSituationPage(int pageNum, int pageSize, Map<String, Object> map);

	int querySituationCount(Map<String, Object> map);

	Situation findSituationById(String situationId);
}
