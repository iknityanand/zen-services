package io.github.iknityanand.zenservices.multiplication.controller;

import org.assertj.core.api.Assertions;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

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

	private JacksonTester<MultiplicationResultAttempt> jsonResult;
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
		MockHttpServletResponse response = mvc.perform(
				post("/results").contentType(MediaType.APPLICATION_JSON).content(jsonResult.write(attempt).getJson()))
				.andReturn().getResponse();

		// then
		Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

		Assertions
				.assertThat(
						response.getContentAsString())
				.isEqualTo(jsonResult.write(new MultiplicationResultAttempt(attempt.getUser(),
						attempt.getMultiplication(), attempt.getResultAttempt(), correct)).getJson());
//        Assertions.assertThat(response.getContentAsString()).isEqualTo(
//                jsonResponse.write(new ResultResponse(correct)).getJson());
	}

}
