package ru.hogwarts.additionalcourseschool.services;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.additionalcourseschool.model.Avatar;

import java.io.IOException;
import java.util.Optional;

public interface AvatarService {

    void uploadAvatar(Long id, MultipartFile file) throws IOException;
    Optional<Avatar> findByStudentId(Long studentId);
}
