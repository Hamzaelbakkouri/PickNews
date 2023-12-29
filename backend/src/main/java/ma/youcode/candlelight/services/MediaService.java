package ma.youcode.candlelight.services;

import ma.youcode.candlelight.models.dto.Medias.MediaDtoReq;
import ma.youcode.candlelight.models.dto.Medias.MediaDtoResp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MediaService extends G_Type<MediaDtoReq, MediaDtoResp, Long> {
    Page<MediaDtoResp> getAllWithPagination(Pageable pageable);
}
