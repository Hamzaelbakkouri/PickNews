package ma.youcode.candlelight.models.documents;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document(collection = "posts")
public class Post {

    @Id
    private Long id;
    private String title;
    private String content;
    private LocalDateTime publishTime;
    private String[] Tags;

    private User publisher;
    private List<Comment> comments;
    private List<PostReaction> reactions;
    private List<Media> media;
}
