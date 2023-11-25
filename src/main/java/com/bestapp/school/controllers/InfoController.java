package com.bestapp.school.controllers;

import com.bestapp.school.services.AppInfoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bestapp.school.dto.AppInfoDTO;

@RestController
@RequestMapping("/info")
@Tag(name = "Information", description = "Information about application")
public class InfoController {
    private final AppInfoService appInfoService;

    public InfoController(AppInfoService appInfoService) {
        this.appInfoService = appInfoService;
    }

    @GetMapping("/appInfo")
    public ResponseEntity<AppInfoDTO> getAppInfo() {
        AppInfoDTO appInfoDTO = appInfoService.getAppInfo();
        if (appInfoDTO == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(appInfoDTO);
    }
}
