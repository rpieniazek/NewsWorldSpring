package pl.pwr.news.webapp.controller.stereotype.dto;

import lombok.Getter;
import lombok.Setter;
import pl.pwr.news.model.article.Article;
import pl.pwr.news.model.stereotype.Stereotype;
import pl.pwr.news.model.tag.Tag;
import pl.pwr.news.webapp.controller.article.dto.ListedArticleDTO;
import pl.pwr.news.webapp.controller.tag.dto.ListedTagDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by jakub on 3/9/16.
 */
@Getter
@Setter
public class StereotypeDTO {

    private Long id;
    private String name;
    private List<ListedTagDTO> tags = new ArrayList<>();

    public StereotypeDTO(Stereotype stereotype) {
        if (stereotype != null) {
            this.id = stereotype.getId();
            this.name = stereotype.getName();
            Set<Tag> tags = stereotype.getTags();
            tags.forEach(tag -> this.tags.add(new ListedTagDTO(tag)));
        }
    }

    public static List<StereotypeDTO> getList(List<Stereotype> stereotypes) {
        List<StereotypeDTO> stereotypeDTOList = new ArrayList<>();
        stereotypes.forEach(stereotype -> stereotypeDTOList.add(new StereotypeDTO(stereotype)));

        return stereotypeDTOList;
    }
}
