package uz.ds.app_word_file.repository;





import org.springframework.data.jpa.repository.JpaRepository;
import uz.ds.app_word_file.entity.AttachmentContent;

import java.util.UUID;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, UUID> {
    AttachmentContent findByAttachmentId(UUID attachment_id);



}
