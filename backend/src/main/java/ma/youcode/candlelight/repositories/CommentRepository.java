package ma.youcode.candlelight.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import graphql.language.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, Long> {}
