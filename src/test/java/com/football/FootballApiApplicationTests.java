package com.football;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import com.football.model.Score;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FootballApiApplicationTests {

	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost";

	private static RestTemplate restTemplate;

	@BeforeAll
	public static void init() {
		restTemplate = new RestTemplate();
	}

	@BeforeEach
	public void setUp() {
		baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/scores");
	}

	@Test
	public void testAddScore() {
		Score score = new Score(0, "Mexico", "Canada", 0, 0);
		Score response = restTemplate.postForObject(baseUrl, score, Score.class);
		assertEquals("Mexico", response.getAwayTeam());
	}

	@Test
	public void testFindScores() {
		prepareTestScores();
		List<Score> response = restTemplate.getForObject(baseUrl, List.class);
		assertEquals(1, response.size());
	}
	
	@Test
	public void testFindScore() {
		prepareTestScores();
		Score response = restTemplate.getForObject(baseUrl + "/{id}", Score.class, 1);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(1, response.getMatchId()),
                () -> assertEquals("Mexico", response.getAwayTeam())
        );
	}
	
	
	@Test
	public void testUpdateScore() {
		prepareTestScores();
		Score score = new Score(1, "Mexico", null, 1, 0);
        restTemplate.put(baseUrl+"/{id}", score, 1);
        Score response = restTemplate.getForObject(baseUrl + "/{id}", Score.class, 1);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(1, score.getAwayScore())
        );
	}
	
	@Test
	public void testDeleteScores() {
		prepareTestScores();
		restTemplate.delete(baseUrl+"/{id}", 1);
		List<Score> response = restTemplate.getForObject(baseUrl, List.class);
		assertEquals(0, response.size());
	}
	

	private void prepareTestScores() {
		Score score = new Score(0, "Mexico", "Canada", 0, 0);
		restTemplate.postForObject(baseUrl, score, Score.class);
	}

}
