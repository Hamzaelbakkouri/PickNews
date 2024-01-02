package ma.youcode.candlelight.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.graphql.data.GraphQlRepository;

import ma.youcode.candlelight.models.documents.User;

@GraphQlRepository
public interface UserRepository extends MongoRepository<User, Long> {
    
    public Optional<User> findByUserName(String userName);
    public List<User> findByUserNameLike(String userName);
    public boolean existsByUserName(String userName);
    public boolean existsByEmailIgnoreCase(String email);
    public boolean existsByPhoneNumber(String phoneNumber);
}
