package ma.youcode.candlelight.services.implementations;

import lombok.AllArgsConstructor;
import ma.youcode.candlelight.exceptions.InvalidCredentials;
import ma.youcode.candlelight.exceptions.ResourceAlreadyExist;
import ma.youcode.candlelight.exceptions.ResourceNotFound;
import ma.youcode.candlelight.models.documents.Media;
import ma.youcode.candlelight.models.dto.Medias.MediaDtoReq;
import ma.youcode.candlelight.models.dto.Medias.MediaDtoResp;
import ma.youcode.candlelight.repositories.MediaRepository;
import ma.youcode.candlelight.services.MediaService;
import ma.youcode.candlelight.services.SequenceGeneratorService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class MediaServiceImpl implements MediaService {

    private MediaRepository mediaRepository;
    private ModelMapper modelMapper;
    private final SequenceGeneratorService sequenceGenerator;

    @Override
    public MediaDtoResp create(MediaDtoReq newElement) {
        Media media = modelMapper.map(newElement, Media.class);
        media.setId(sequenceGenerator.generateSequence(Media.getSequenceName()));
        return modelMapper.map(mediaRepository.save(media), MediaDtoResp.class);

    }

    @Override
    public MediaDtoResp update(MediaDtoReq newElement) {
        if (newElement.getId() != null) {
            Optional<Media> mediaToUpdate = this.mediaRepository.findById(newElement.getId());
            if (mediaToUpdate.isPresent()) {
                Media media = this.modelMapper.map(newElement, Media.class);
                media.setId(mediaToUpdate.get().getId());
                return this.modelMapper.map(this.mediaRepository.save(media), MediaDtoResp.class);
            }
            throw new ResourceNotFound("Media with id " + newElement.getId() + " Not Exist");
        }
        throw new ResourceNotFound("There's no Id to update");
    }

    @Override
    public MediaDtoResp findById(Long aLong) {
        Optional<Media> mediaToUpdate = this.mediaRepository.findById(aLong);
        if (mediaToUpdate.isPresent()) {
            return this.modelMapper.map(mediaToUpdate.get(), MediaDtoResp.class);
        }
        throw new ResourceNotFound("Media with id " + aLong + " Not Exist");
    }

    @Override
    public Page<MediaDtoResp> getAllWithPagination(Pageable pageable) {
        Page<Media> medias = mediaRepository.findAll(pageable);
        return medias.map(media -> modelMapper.map(media, MediaDtoResp.class));
    }

    @Override
    public String delete(Long aLong) {
        Optional<Media> mediaToUpdate = this.mediaRepository.findById(aLong);
        if (mediaToUpdate.isPresent()) {
            this.mediaRepository.deleteById(mediaToUpdate.get().getId());
            return "Media with id " + aLong + " Deleted successfully";
        }
        throw new ResourceNotFound("Media with id " + aLong + " Not Exist");
    }

}
