package ma.youcode.candlelight.controller;

import lombok.AllArgsConstructor;
import ma.youcode.candlelight.models.dto.posts.PostDtoReq;
import ma.youcode.candlelight.models.dto.posts.PostDtoResp;
import ma.youcode.candlelight.services.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;


@AllArgsConstructor
@Controller
public class PostController {
    private PostService postService;

    @MutationMapping
    public PostDtoResp createPost(final @Argument PostDtoReq newPost) {
        return this.postService.create(newPost);
    }

    @MutationMapping
    public PostDtoResp updatePost(final @Argument PostDtoReq postDtoReq) {
        return this.postService.update(postDtoReq);
    }

    @QueryMapping
    public Page<PostDtoResp> PaginatePost(@Argument(name = "page") Integer page, @Argument(name = "pageSize") Integer pageSize) {
        page = page != null ? page : 0;
        pageSize = pageSize != null ? pageSize : 10;

        Pageable pageable = PageRequest.of(page, pageSize);
        return this.postService.getAllWithPagination(pageable);
    }

}
