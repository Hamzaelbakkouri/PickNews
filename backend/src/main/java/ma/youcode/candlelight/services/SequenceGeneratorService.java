package ma.youcode.candlelight.services;

import org.springframework.data.mongodb.core.MongoOperations;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ma.youcode.candlelight.models.sequences.DataBaseSequence;

@AllArgsConstructor

@Service
public class SequenceGeneratorService {
    
    private MongoOperations mongoOperations;

    public long generateSequence(String seqName) {
        Query query = new Query(Criteria.where("id").is(seqName));
        Update update = new Update().inc("seq", 1);
    
        DataBaseSequence counter = mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true), DataBaseSequence.class);
    
        return (counter != null) ? counter.getSeq() : 1;
    }

}

