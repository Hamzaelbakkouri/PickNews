package ma.youcode.candlelight.services;

import ma.youcode.candlelight.models.dto.posts.PostDtoInsertion;
import ma.youcode.candlelight.models.dto.posts.PostDtoReq;
import ma.youcode.candlelight.models.dto.posts.PostDtoResp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    PostDtoInsertion create(PostDtoReq newElement);

    PostDtoResp update(PostDtoReq elementBody);

    PostDtoResp findById(Long identifier);

    String delete(Long identifier);

    Page<PostDtoResp> getAllWithPagination(Pageable pageable);

    List<PostDtoResp> PostsByUser(Long id);

    List<PostDtoResp> searchByTitle(String title);
}
