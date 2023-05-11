package unitarios;

import com.everis.d4i.tutorial.controllers.TvShowController;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.TvShowService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@RunWith(SpringRunner.class)
@WebMvcTest(TvShowController.class)
public class TvShowControllerTest {

    @Mock
    private TvShowService tvShowService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private TvShowController tvShowController;

    @Test
    public void testGetTvShowsByCategory() throws Exception {
        Long categoryId = 1L;
        List<TvShowRest> tvShows = new ArrayList<>();
        tvShows.add(new TvShowRest());
        when(tvShowService.getTvShowsByCategory(categoryId)).thenReturn(tvShows);

        MvcResult result = mockMvc.perform(get("/tvshows")
                        .param("categoryId", categoryId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        NetflixResponse<List<TvShowRest>> response = objectMapper.readValue(content, new TypeReference<NetflixResponse<List<TvShowRest>>>() {});
        assertEquals(tvShows, response.getData());
    }

    @Test
    public void testGetTvShowById() throws Exception {
        Long id = 1L;
        TvShowRest tvShow = new TvShowRest();
        when(tvShowService.getTvShowById(id)).thenReturn(tvShow);

        MvcResult result = mockMvc.perform(get("/tvshows/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        NetflixResponse<TvShowRest> response = objectMapper.readValue(content, new TypeReference<NetflixResponse<TvShowRest>>() {});
        assertEquals(tvShow, response.getData());
    }

    @Test
    public void testUpdateTvShow() throws Exception {
        Long id = 1L;
        TvShowRest tvShow = new TvShowRest();
        tvShow.setId(id);
        when(tvShowService.updateTvShow(tvShow)).thenReturn(tvShow);

        String requestBody = objectMapper.writeValueAsString(tvShow);

        MvcResult result = mockMvc.perform(put("/tvshows/{id}", id)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        NetflixResponse<TvShowRest> response = objectMapper.readValue(content, new TypeReference<NetflixResponse<TvShowRest>>() {});
        assertEquals(tvShow, response.getData());
    }

    @Test
    public void testDeleteTvShow() throws Exception {
        Long id = 1L;
        TvShowRest tvShow = new TvShowRest();
        when(tvShowService.deleteTvShow(id)).thenReturn(tvShow);

        MvcResult result = mockMvc.perform(delete("/tvshows/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        NetflixResponse<TvShowRest> response = objectMapper.readValue(content, new TypeReference<NetflixResponse<TvShowRest>>() {});
        assertEquals(tvShow, response.getData());
    }
}