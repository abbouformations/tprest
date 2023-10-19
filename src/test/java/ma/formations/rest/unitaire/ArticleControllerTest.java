package ma.formations.rest.unitaire;

import ma.formations.rest.domaine.ArticleDTO;
import ma.formations.rest.service.IService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ArticleControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private IService service;

    @Test
    void testGetAll() throws Exception {
        List<ArticleDTO> articles = new ArrayList<>
                (Arrays.asList(
                        new ArticleDTO(1L, "PC PORTABLE HP I7", 15000d, 10d),
                        new ArticleDTO(2L, "ECRAN", 1500d, 10d),
                        new ArticleDTO(3L, "CAMERA LG", 3000d, 10d),
                        new ArticleDTO(4L, "SOURIS", 200d, 10d)));


        when(service.getAll()).thenReturn(articles);
        mvc.perform(get("/api/articles/all").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].description").value("ECRAN"))
                .andExpect(jsonPath("$[2].price").value(3000d))
                .andExpect(jsonPath("$[3].quantity").value(10d));
    }

}
