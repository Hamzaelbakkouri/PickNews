package ma.youcode.candlelight.models.sequences;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Document(collection = "database_sequences")
public class DataBaseSequence {

    @Id
    private String id;
    private long seq;
}
