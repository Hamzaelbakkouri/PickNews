package ma.youcode.candlelight.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.youcode.candlelight.models.enums.MediaType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document(collection = "media")
public class Media {

    @Transient
    private static final String SEQUENCE_NAME = "media_sequence";

    public static String getSequenceName() {
        return SEQUENCE_NAME;
    }

    @Id
    private Long id;
    private String url;
    private MediaType type;

    private Post post;
}
