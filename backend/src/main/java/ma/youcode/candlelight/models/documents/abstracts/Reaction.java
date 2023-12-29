package ma.youcode.candlelight.models.documents.abstracts;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.youcode.candlelight.models.documents.User;
import ma.youcode.candlelight.models.enums.ReactionType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "reactions")
public abstract class Reaction {
    
    @Id
    protected Long id;
    
    protected User reactor;
    protected ReactionType type;
}
