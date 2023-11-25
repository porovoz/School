package com.bestapp.school.services;

import org.springframework.web.multipart.MultipartFile;
import com.bestapp.school.model.Avatar;

import java.io.IOException;
import java.util.Optional;

public interface AvatarService {

    void uploadAvatar(Long id, MultipartFile file) throws IOException;
    Optional<Avatar> findByStudentId(Long studentId);
}
