package ma.youcode.candlelight.models.dto.posts;

import lombok.Getter;
import lombok.Setter;
import ma.youcode.candlelight.models.documents.Comment;
import ma.youcode.candlelight.models.documents.PostReaction;
import ma.youcode.candlelight.models.documents.User;
import ma.youcode.candlelight.models.dto.Medias.MediaDtoResp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostDtoResp {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime publishTime;
    private List<String> Tags;

    private User publisher;
    private List<Comment> comments;
    private List<PostReaction> reactions;
    private List<MediaDtoResp> media;
}
