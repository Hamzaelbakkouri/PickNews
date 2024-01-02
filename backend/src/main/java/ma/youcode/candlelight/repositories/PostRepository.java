package ma.youcode.candlelight.repositories;

import ma.youcode.candlelight.models.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ma.youcode.candlelight.models.documents.Post;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, Long> {
    List<Post> findPostsByPublisher(User publisher);

    List<Post> findPostsByTitleLike(String title);
}
