package com.progetto.projectmanagement.repository.attachment;

import com.progetto.projectmanagement.domain.attachment.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
}
