package com.football.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.football.model.Score;
import com.football.service.ScoresService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/scores")
@Api(tags = {"Football API"})
@Validated
public class ScoresController {

	@Autowired
	private ScoresService scoresService;

	static final Logger log = LoggerFactory.getLogger(ScoresController.class);

	@GetMapping("/{matchId}")
	@ApiOperation(value = "View match details with its ID", response = Score.class)
	public ResponseEntity<Score> findScore(@PathVariable Integer matchId) {
		log.info("Find a match detatilas with its id " + matchId);
		return ResponseEntity.status(HttpStatus.OK).body(scoresService.findMatch(matchId));
	}

	@GetMapping()
	@ApiOperation(value = "View a list of available match details", response = Score.class)
	public ResponseEntity<List<Score>> findScores() {
		log.info("Find all match detatilas");
		return ResponseEntity.status(HttpStatus.OK).body(scoresService.findAll());
	}

	@PostMapping
	@ResponseBody
	@ApiOperation(value = "Add a match details", response = Score.class)
	public ResponseEntity<Score> addScore(@Valid @RequestBody Score score) {
		log.info("Add a match");
		return ResponseEntity.status(HttpStatus.CREATED).body(scoresService.saveScore(score));
	}

	@PutMapping("/{matchId}")
	@ResponseBody
	@ApiOperation(value = "Update goals details of match", response = Score.class)
	public ResponseEntity<Score> updateScore(@PathVariable Integer matchId, @RequestBody Score score) {
		log.info("Update a matach details of " + matchId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(scoresService.updateScore(score));
	}

	@DeleteMapping("/{matchId}")
	@ApiOperation(value = "Delete a match")
	public ResponseEntity<Integer> deleteScore(@PathVariable Integer matchId) {
		log.info("Delete a match " + matchId);
		boolean isRemoved = scoresService.deleteScore(matchId);
		if (!isRemoved) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(matchId, HttpStatus.OK);
	}
}
