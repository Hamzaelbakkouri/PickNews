package ma.youcode.candlelight.models.dto.posts;

import lombok.Getter;
import lombok.Setter;
import ma.youcode.candlelight.models.documents.User;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDtoInsertion {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime publishTime;
    private String[] Tags;

    private User publisher;
}
