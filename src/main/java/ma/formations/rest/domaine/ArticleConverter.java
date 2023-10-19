package ma.formations.rest.domaine;

import ma.formations.rest.service.service.model.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleConverter {

    public static Article toBO(ArticleDTO dto) {
        if (dto == null) return null;
        return new Article(dto.getId(), dto.getDescription(), dto.getPrice(), dto.getQuantity());
    }

    public static ArticleDTO toDTO(Article bo) {
        if (bo == null) return null;
        return new ArticleDTO(bo.getId(), bo.getDescription(), bo.getPrice(), bo.getQuantity());
    }

    public static List<Article> toBOs(List<ArticleDTO> dtoList) {
        List<Article> boList = new ArrayList<>();
        dtoList.forEach(a -> boList.add(toBO(a)));
        return boList;
    }

    public static List<ArticleDTO> toDTOs(List<Article> boList) {
        List<ArticleDTO> dtoList = new ArrayList<>();
        boList.forEach(a -> dtoList.add(toDTO(a)));
        return dtoList;
    }
}
