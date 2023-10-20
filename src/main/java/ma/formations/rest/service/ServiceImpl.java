package ma.formations.rest.service;

import lombok.AllArgsConstructor;
import ma.formations.rest.dao.IDao;
import ma.formations.rest.domaine.ArticleConverter;
import ma.formations.rest.domaine.ArticleDTO;
import ma.formations.rest.service.exception.BusinessException;
import ma.formations.rest.service.service.model.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceImpl implements IService {
    private IDao dao;

    @Override
    public ArticleDTO getById(Long id) {
        Article articleFound = dao.findById(id);
        if (articleFound == null) {
            throw new BusinessException(String.format("no article with id= %s exist", id));
        }
        return ArticleConverter.toDTO(articleFound);
    }

    @Override
    public List<ArticleDTO> getAll() {
        return ArticleConverter.toDTOs(dao.findAll());
    }

    @Override
    public void create(ArticleDTO article) {
        if (article.getId() == null) {
            dao.save(ArticleConverter.toBO(article));
            return;
        }
        Article articleFound = dao.findAll().stream().
                filter(a -> article.getId().equals(a.getId())).
                findFirst().orElse(null);

        if (articleFound != null)
            throw new BusinessException(String.format("Article with the same Id=%s exist in database", article.getId()));

        dao.save(ArticleConverter.toBO(article));
    }

    @Override
    public void update(Long id, ArticleDTO article) {
        if (id == null)
            throw new BusinessException(String.format("Article id=%s should not be null", id));

        dao.findAll().stream().
                filter(a -> a.getId().equals(id)).
                findFirst().orElseThrow(() -> new BusinessException(String.format("No article with the id=%s exist in database", id)));

        article.setId(id);
        dao.save(ArticleConverter.toBO(article));
        
    }

    @Override
    public void deleteById(Long id) {
        Article articleFound = dao.findById(id);
        if (articleFound == null) {
            throw new BusinessException(String.format("no article with id= %s exist", id));
        }
        dao.deleteById(id);
    }
}
