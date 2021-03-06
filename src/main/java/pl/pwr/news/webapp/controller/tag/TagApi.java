package pl.pwr.news.webapp.controller.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pwr.news.factory.TagFactory;
import pl.pwr.news.model.tag.Tag;
import pl.pwr.news.service.tag.TagService;
import pl.pwr.news.webapp.controller.Response;
import pl.pwr.news.webapp.controller.tag.dto.TagDTO;

import java.util.List;

/**
 * Created by jakub on 3/9/16.
 */
@RestController
@RequestMapping(value = "/api", produces = "application/json;charset=UTF-8")
public class TagApi {

    @Autowired
    TagService tagService;

    @Autowired
    TagFactory tagFactory;

    @RequestMapping(value = "/tag", method = RequestMethod.GET)
    public Response<List<TagDTO>> getTags() {
        List<Tag> tagList = tagService.findAll();
        List<TagDTO> tagDTOList = TagDTO.getList(tagList);

        return new Response<>(tagDTOList);
    }

    @RequestMapping(value = "/tag/{tagId}", method = RequestMethod.GET)
    public Response<TagDTO> getTag(@PathVariable("tagId") Long tagId) {

        if (!tagService.exist(tagId)) {
            return new Response<>("-1", "Tag not found");
        }
        TagDTO tagDTO = new TagDTO(tagService.findById(tagId));

        return new Response<>(tagDTO);
    }

    @RequestMapping(value = "/tag", method = RequestMethod.POST)
    public Response<TagDTO> saveTag(@RequestParam String name) {
        Tag tag = tagFactory.getInstance(name);
        tagService.createTag(tag);
        TagDTO tagDTO = new TagDTO(tag);
        return new Response<>(tagDTO);
    }
}
