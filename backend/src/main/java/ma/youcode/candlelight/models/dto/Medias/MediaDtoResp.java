package ma.youcode.candlelight.models.dto.Medias;

import lombok.Getter;
import lombok.Setter;
import ma.youcode.candlelight.models.enums.MediaType;

@Getter
@Setter
public class MediaDtoResp {
    private Long id;
    private String url;
    private MediaType type;

}
