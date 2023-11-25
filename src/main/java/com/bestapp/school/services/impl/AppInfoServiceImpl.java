package com.bestapp.school.services.impl;

import com.bestapp.school.services.AppInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.bestapp.school.dto.AppInfoDTO;

@Service
public class AppInfoServiceImpl implements AppInfoService {

    @Value("${app.env}")
    private String appEnv;
    @Override
    public AppInfoDTO getAppInfo() {
        AppInfoDTO appInfoDTO = new AppInfoDTO();
        appInfoDTO.setAppName("hogwarts-school");
        appInfoDTO.setAppVersion("0.0.1");
        appInfoDTO.setAppEnvironment(appEnv);
        return appInfoDTO;
    }
}
