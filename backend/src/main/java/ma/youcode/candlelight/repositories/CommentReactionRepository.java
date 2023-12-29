package ma.youcode.candlelight.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ma.youcode.candlelight.models.documents.CommentReaction;

@Repository
public interface CommentReactionRepository extends MongoRepository<CommentReaction, Long> {}
