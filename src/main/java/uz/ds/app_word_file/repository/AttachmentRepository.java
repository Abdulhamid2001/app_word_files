package uz.ds.app_word_file.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import uz.ds.app_word_file.entity.Attachment;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}
