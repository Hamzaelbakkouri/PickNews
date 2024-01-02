package ma.youcode.candlelight.models.dto.posts;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class PostDtoReq {
    private Long id;

    @NotBlank(message = "Title must Be Not Blank")
    private String title;

    @NotBlank(message = "Content must Be Not Blank")
    private String content;

//    @FutureOrPresent(message = "PublishTime Must Be on Present")
//    private LocalDateTime publishTime;

    @NotNull(message = "Tags must be Not Null")
    private List<String> Tags;

    @NotNull(message = "Publisher must be Not Null")
    private Long publisher;
}
