package ma.youcode.candlelight.models.documents;

import java.time.LocalDate;
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

@Document(collection = "users")
public class User {

    @Id
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;

    @CreatedDate
    private LocalDate joinedAt; 

    private List<Post> posts;
    private List<Comment> comments;
    private List<PostReaction> Postreactions;
    private List<CommentReaction> commentReactions;
}
