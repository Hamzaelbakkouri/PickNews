package ma.youcode.candlelight.services;

import ma.youcode.candlelight.models.dto.posts.PostDtoReq;
import ma.youcode.candlelight.models.dto.posts.PostDtoResp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService extends G_Type<PostDtoReq, PostDtoResp, Long> {
    Page<PostDtoResp> getAllWithPagination(Pageable pageable);
}
