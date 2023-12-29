package ma.youcode.candlelight.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ma.youcode.candlelight.models.documents.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    public boolean existsByEmailIgnoreCase(String email);
    public boolean existsByPhoneNumber(String phoneNumber);
}
