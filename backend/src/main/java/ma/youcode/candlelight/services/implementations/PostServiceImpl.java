package ma.youcode.candlelight.services.implementations;

import lombok.AllArgsConstructor;
import ma.youcode.candlelight.exceptions.ResourceNotFound;
import ma.youcode.candlelight.models.documents.Post;
import ma.youcode.candlelight.models.documents.User;
import ma.youcode.candlelight.models.dto.posts.PostDtoInsertion;
import ma.youcode.candlelight.models.dto.posts.PostDtoReq;
import ma.youcode.candlelight.models.dto.posts.PostDtoResp;
import ma.youcode.candlelight.repositories.PostRepository;
import ma.youcode.candlelight.repositories.UserRepository;
import ma.youcode.candlelight.services.PostService;
import ma.youcode.candlelight.services.SequenceGeneratorService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private final SequenceGeneratorService sequenceGenerator;

    @Override
    public PostDtoInsertion create(PostDtoReq newElement) {
        Post post = this.modelMapper.map(newElement, Post.class);
        Optional<User> user = this.userRepository.findById(newElement.getPublisher());
        if (user.isPresent()) {
            post.setPublishTime(LocalDateTime.now());
            post.setId(sequenceGenerator.generateSequence(Post.getSequenceName()));
            post.setPublisher(user.get());
            return this.modelMapper.map(postRepository.save(post), PostDtoInsertion.class);
        }
        throw new ResourceNotFound("User Not Found !!");
    }

    @Override
    public PostDtoResp update(PostDtoReq elementBody) {
        Optional<Post> post = this.postRepository.findById(elementBody.getId());
        Optional<User> user = this.userRepository.findById(elementBody.getPublisher());
        if (post.isPresent()) {
            Post postToUpdate = this.modelMapper.map(elementBody, Post.class);
            postToUpdate.setId(elementBody.getId());
            postToUpdate.setPublishTime(post.get().getPublishTime());
            postToUpdate.setPublisher(user.get());
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
        throw new ResourceNotFound("Post With Id :" + aLong + " Not Found");
    }

    @Override
    public Page<PostDtoResp> getAllWithPagination(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);
        return posts.map(post -> modelMapper.map(post, PostDtoResp.class));
    }

    @Override
    public List<PostDtoResp> PostsByUser(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            List<Post> posts = this.postRepository.findPostsByPublisher(user.get());
            if (posts != null) {
                return posts.stream().map(post -> this.modelMapper.map(post, PostDtoResp.class)).collect(Collectors.toList());
            }
            throw new ResourceNotFound("Posts Not Found");
        }
        throw new ResourceNotFound("User Not !!");
    }

    @Override
    public List<PostDtoResp> searchByTitle(String title) {
        List<Post> posts = this.postRepository.findPostsByTitleLike(title);
        if (posts != null) {
            return posts.stream().map(post -> modelMapper.map(post, PostDtoResp.class)).collect(Collectors.toList());
        }
        throw new ResourceNotFound("No Post Found");
    }
}
