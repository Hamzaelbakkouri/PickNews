package ma.youcode.candlelight.models.documents;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.youcode.candlelight.models.documents.abstracts.Reaction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document(collection = "reactions")
public final class PostReaction extends Reaction {
    
    private Post post;
}
