package com.football.model;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;


public class Score {

	@ApiModelProperty(notes = "The unique identifier for each match")
	Integer matchId;
	@NotNull(message = "The away team name should not be null")
	@NotEmpty(message = "The away team name should not be empty")
	@ApiModelProperty(notes = "The away team name")
	String awayTeam;
	@NotNull(message = "The home team name should not be null")
	@NotEmpty(message = "The home team name should not be empty")
	@ApiModelProperty(notes = "The home team name")
	String homeTeam;
	@ApiModelProperty(notes = "The goal deatils of away team")
	int awayScore;
	@ApiModelProperty(notes = "The goal deatils of home team")
	int homeScore;


	public Integer getMatchId() {
		return matchId;
	}

	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public int getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}

	public int getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}

	public Score() {
		super();
	}

	public Score(Integer matchId,
			@NotNull(message = "The away team name should not be null") @NotEmpty(message = "The away team name should not be empty") String awayTeam,
			@NotNull(message = "The home team name should not be null") @NotEmpty(message = "The home team name should not be empty") String homeTeam,
			int awayScore, int homeScore) {
		super();
		this.matchId = matchId;
		this.awayTeam = awayTeam;
		this.homeTeam = homeTeam;
		this.awayScore = awayScore;
		this.homeScore = homeScore;
	}

}
