package ma.youcode.candlelight.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.youcode.candlelight.models.dto.Medias.MediaDtoReq;
import ma.youcode.candlelight.models.dto.Medias.MediaDtoResp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import ma.youcode.candlelight.services.MediaService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;


@AllArgsConstructor
@Controller
public class MediaController {

    private final MediaService mediaService;

    @MutationMapping
    public MediaDtoResp createMedia(final @Valid @Argument MediaDtoReq newMedia) {
        return this.mediaService.create(newMedia);
    }

    @MutationMapping
    public MediaDtoResp updateMedia(final @Valid @Argument MediaDtoReq Media) {
        return this.mediaService.update(Media);
    }

    @QueryMapping(name = "Medias")
    public Page<MediaDtoResp> Medias(@Argument(name = "page") Integer page, @Argument(name = "pageSize") Integer pageSize) {
        page = page != null ? page : 0;
        pageSize = pageSize != null ? pageSize : 10;

        Pageable pageable = PageRequest.of(page, pageSize);
        return mediaService.getAllWithPagination(pageable);
    }

    @QueryMapping
    public String deleteMedia(@Argument(name = "id") Long id) {
        return this.mediaService.delete(id);
    }
}
