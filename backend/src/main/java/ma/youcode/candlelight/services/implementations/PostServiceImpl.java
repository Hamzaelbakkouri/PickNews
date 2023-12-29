package ma.youcode.candlelight.services.implementations;

import lombok.AllArgsConstructor;
import ma.youcode.candlelight.exceptions.ResourceNotFound;
import ma.youcode.candlelight.models.documents.Media;
import ma.youcode.candlelight.models.documents.Post;
import ma.youcode.candlelight.models.dto.Medias.MediaDtoResp;
import ma.youcode.candlelight.models.dto.posts.PostDtoReq;
import ma.youcode.candlelight.models.dto.posts.PostDtoResp;
import ma.youcode.candlelight.repositories.PostRepository;
import ma.youcode.candlelight.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper modelMapper;

    @Override
    public PostDtoResp create(PostDtoReq newElement) {
        Post post = this.modelMapper.map(newElement, Post.class);
        post.setPublishTime(LocalDateTime.now());
        return this.modelMapper.map(postRepository.save(post), PostDtoResp.class);
    }

    @Override
    public PostDtoResp update(PostDtoReq elementBody) {
        Optional<Post> post = this.postRepository.findById(elementBody.getId());
        if (post.isPresent()) {
            Post postToUpdate = this.modelMapper.map(elementBody, Post.class);
            postToUpdate.setId(elementBody.getId());
            return this.modelMapper.map(this.postRepository.save(postToUpdate), PostDtoResp.class);
        }
        throw new ResourceNotFound("Post With Id : " + elementBody.getId() + " Not Found");
    }

    @Override
    public PostDtoResp findById(Long aLong) {
        Optional<Post> post = this.postRepository.findById(aLong);
        if (post.isPresent()) {
            return this.modelMapper.map(post.get(), PostDtoResp.class);
        }
        throw new ResourceNotFound("Post With Id : " + aLong + " Not Found");
    }

    @Override
    public String delete(Long aLong) {
        Optional<Post> post = this.postRepository.findById(aLong);
        if (post.isPresent()) {
            this.postRepository.deleteById(aLong);
            return "Post With ID : " + aLong + " deleted Successfully";
        }
        return "Post With ID : " + aLong + " Not Found";
    }

    @Override
    public Page<PostDtoResp> getAllWithPagination(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);
        return posts.map(post -> modelMapper.map(post, PostDtoResp.class));
    }
}
