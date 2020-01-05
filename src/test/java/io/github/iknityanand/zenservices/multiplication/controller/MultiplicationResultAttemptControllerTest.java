package io.github.iknityanand.zenservices.multiplication.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.iknityanand.zenservices.domain.Multiplication;
import io.github.iknityanand.zenservices.domain.MultiplicationResultAttempt;
import io.github.iknityanand.zenservices.domain.User;
import io.github.iknityanand.zenservices.service.MultiplicationService;

@RunWith(SpringRunner.class)
@WebMvcTest(MultiplicationResultAttemptControllerTest.class)
public class MultiplicationResultAttemptControllerTest<ResultResponse> {

	@MockBean
	private MultiplicationService multiplicationService;

	@Autowired
	private MockMvc mvc;

	private JacksonTester<MultiplicationResultAttempt> jsonResultAttempt;
	private JacksonTester<List<MultiplicationResultAttempt>> jsonResultAttemptList;
	private JacksonTester<ResultResponse> jsonResponse;

	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	public void postResultReturnCorrect() throws Exception {
		genericParameterizedTest(true);
	}

	@Test
	public void postResultReturnNotCorrect() throws Exception {
		genericParameterizedTest(false);
	}

	void genericParameterizedTest(final boolean correct) throws Exception {
		// given (remember we're not testing here the service itself)
		given(multiplicationService.checkAttempt(any(MultiplicationResultAttempt.class))).willReturn(correct);
		User user = new User("john");
		Multiplication multiplication = new Multiplication(50, 70);
		MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3500, correct);

		// when
		MockHttpServletResponse response = mvc.perform(post("/results").contentType(MediaType.APPLICATION_JSON)
				.content(jsonResultAttempt.write(attempt).getJson())).andReturn().getResponse();

		// then
		Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

		Assertions
				.assertThat(
						response.getContentAsString())
				.isEqualTo(jsonResultAttempt.write(new MultiplicationResultAttempt(attempt.getUser(),
						attempt.getMultiplication(), attempt.getResultAttempt(), correct)).getJson());
//        Assertions.assertThat(response.getContentAsString()).isEqualTo(
//                jsonResponse.write(new ResultResponse(correct)).getJson());
	}

	@Test
	public void getUserStats() throws Exception {
		// given
		User user = new User("john_doe");
		Multiplication multiplication = new Multiplication(50, 70);
		MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3500, true);
		List<MultiplicationResultAttempt> recentAttempts = Lists.newArrayList(attempt, attempt);

		// given
		given(multiplicationService.getStatsForUser("john_doe")).willReturn(recentAttempts);

		// when
		MockHttpServletResponse response = mvc.perform(get("/results").param("alias", "john_doe")).andReturn()
				.getResponse();
		// then 
		Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		Assertions.assertThat(response.getContentAsString()).isEqualTo(
		 jsonResultAttemptList.write( recentAttempts ).getJson());
	}

}
