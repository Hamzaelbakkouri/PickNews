package ma.youcode.candlelight.controller;

import lombok.AllArgsConstructor;
import ma.youcode.candlelight.models.dto.posts.PostDtoInsertion;
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

import java.util.List;


@AllArgsConstructor
@Controller
public class PostController {
    private PostService postService;

    @MutationMapping
    public PostDtoInsertion createPost(final @Argument PostDtoReq newPost) {
        return this.postService.create(newPost);
    }

    @QueryMapping
    public String deletePost(@Argument Long aLong) {
        return this.postService.delete(aLong);
    }

    @MutationMapping
    public PostDtoResp updatePost(final @Argument PostDtoReq Post, final @Argument Long id) {
        Post.setId(id);
        return this.postService.update(Post);
    }

    @QueryMapping
    public Page<PostDtoResp> PaginatePost(@Argument(name = "page") Integer page, @Argument(name = "pageSize") Integer pageSize) {
        page = page != null ? page : 0;
        pageSize = pageSize != null ? pageSize : 10;

        Pageable pageable = PageRequest.of(page, pageSize);
        return this.postService.getAllWithPagination(pageable);
    }

    @QueryMapping
    public PostDtoResp postById(final @Argument Long id) {
        return this.postService.findById(id);
    }

    @QueryMapping
    public List<PostDtoResp> postsByUser(final @Argument Long id) {
        return this.postService.PostsByUser(id);
    }

    @QueryMapping
    public List<PostDtoResp> postsTitleLike(final @Argument String title) {
        return this.postService.searchByTitle(title);
    }

}
