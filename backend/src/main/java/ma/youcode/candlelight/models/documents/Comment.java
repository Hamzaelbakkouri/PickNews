package ma.youcode.candlelight.models.documents;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
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

@Document(collection = "comments")
public class Comment {
    
    @Id
    private Long id;
    private String content;

    @CreatedDate
    private LocalDateTime publishTime;

    private Post post;
    private User publisher;
    private List<CommentReaction> reactions;
}
