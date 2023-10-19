package ma.formations.rest.integration;

import ma.formations.rest.domaine.ArticleDTO;
import ma.formations.rest.service.IService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ArticleControllerTest {
    @Autowired
    private IService service;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void testGetById() throws Exception {

        ArticleDTO article = new ArticleDTO(1L, "PC PORTABLE HP I7", 15000d, 10d);

        Long id = 1L;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(new MediaType[]{MediaType.APPLICATION_JSON}));

        HttpEntity<ArticleDTO> entity = new HttpEntity<ArticleDTO>(headers);
        ResponseEntity<ArticleDTO> result = this.restTemplate.exchange("http://localhost:" + port + "/api/articles/id/" + article.getId(), HttpMethod.GET,
                entity, ArticleDTO.class);
        assertThat(result).isNotNull();
        ArticleDTO dto = result.getBody();
        assertThat(dto.getId()).isEqualTo(article.getId());
        assertThat(dto.getDescription()).isEqualTo(article.getDescription());
        assertThat(dto.getPrice()).isEqualTo(article.getPrice());
        assertThat(dto.getQuantity()).isEqualTo(article.getQuantity());
    }

}
