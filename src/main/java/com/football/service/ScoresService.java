package com.football.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.football.model.Score;

@Service
public class ScoresService {

	private Map<Integer, Score> scoreDetails = new LinkedHashMap<Integer, Score>();

	public Score saveScore(Score score) {

		int nextIndex = scoreDetails.size() + 1;
		score.setMatchId(nextIndex);
		scoreDetails.put(nextIndex, score);
		return score;
	}

	public Score updateScore(Score score) {
		int nextIndex = scoreDetails.size() + 1;
		if (scoreDetails.containsKey(score.getMatchId())) {
			int matchId = score.getMatchId();
			Score oldScore = scoreDetails.get(matchId);
			if (null != score.getHomeTeam() && oldScore.getHomeTeam().equals(score.getHomeTeam())
					&& score.getHomeScore() > 0) {
				oldScore.setHomeScore(oldScore.getHomeScore() + 1);
			}

			if (null != score.getAwayTeam() && oldScore.getAwayTeam().equals(score.getAwayTeam())
					&& score.getAwayScore() > 0) {
				oldScore.setAwayScore(oldScore.getAwayScore() + 1);
			}
			scoreDetails.put(matchId, oldScore);
			return oldScore;
		} else {
			score.setMatchId(nextIndex);
			scoreDetails.put(nextIndex, score);
			return score;
		}
	}

	public boolean deleteScore(Integer matchId) {
		boolean deleteResponse = false;
		if (scoreDetails.containsKey(matchId)) {
			scoreDetails.remove(matchId);
			deleteResponse = true;
		}
		return deleteResponse;
	}

	public Score findMatch(Integer matchId) {
		if (null != scoreDetails.get(matchId))
			return scoreDetails.get(matchId);
		else
			return null;
	}

	public List<Score> findAll() {
		List<Score> scoreList = new ArrayList<Score>();
		scoreDetails.forEach((k, v) -> scoreList.add(v));
		return scoreList;
	}

}
