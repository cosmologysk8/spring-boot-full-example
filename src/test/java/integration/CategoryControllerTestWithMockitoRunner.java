package integration;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.everis.d4i.tutorial.controllers.impl.CategoryControllerImpl;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.impl.CategoryServiceImpl;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import com.everis.d4i.tutorial.utils.constants.RestConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTestWithMockitoRunner {

	private MockMvc mockMvc;

	@InjectMocks
	private CategoryControllerImpl categoryControllerImpl;

	@Mock
	private CategoryServiceImpl categoryServiceImpl;


	@BeforeClass
	public static void runBeforeAllTestOfThisClass() {
		System.out.println("Run before all test of this class!");
	}

	@Before
	public void setUpMockMvc() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(categoryControllerImpl).build();
	}

	@Before
	public void runBeforeIndividualTestOfThisClass() throws Exception {
		System.out.println("Run before individual test of this class!");
	}

	@AfterClass
	public static void runAfterAllTestOfThisClass() {
		System.out.println("Run after all test of this class!");
	}

	@After
	public void runAfterIndividualTestOfThisClass() {
		System.out.println("Run after individual test of this class!");
	}

	@Test
	public void getCategories() throws Exception {

		when(categoryServiceImpl.getCategories()).thenReturn(GetMocksForTesting.getMockListCategoryRest());

		MvcResult result = mockMvc.perform(
						get(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_CATEGORY)
								.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().json("{\"status\":\"Success\",\"code\":\"200\"}",false))
				.andExpect(jsonPath("$.code", is(String.valueOf(HttpStatus.OK))))
				.andExpect(jsonPath("$.message", is(CommonConstants.OK)))
				.andExpect(jsonPath("$.status", is(CommonConstants.SUCCESS)))
				.andExpect(jsonPath("$.data.length()", is(4)))
				.andReturn();
		System.out.println(result.getResponse().getContentAsString());
		verify(categoryServiceImpl, only()).getCategories();
	}

}
