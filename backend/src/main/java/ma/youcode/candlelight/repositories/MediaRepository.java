package ma.youcode.candlelight.repositories;


import ma.youcode.candlelight.models.dto.Medias.MediaDtoResp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ma.youcode.candlelight.models.documents.Media;

@Repository
public interface MediaRepository extends MongoRepository<Media, Long> {

}
