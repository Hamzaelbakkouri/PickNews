package ma.youcode.candlelight.models.dto.Medias;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import ma.youcode.candlelight.models.enums.MediaType;

@Getter
@Setter
public class MediaDtoReq {
    private Long id;
    @NotBlank(message = "Url Must Be Not Blank")
    private String url;
    @NotBlank(message = "Media Type Must Be Not Blank")
    private MediaType type;
}   
