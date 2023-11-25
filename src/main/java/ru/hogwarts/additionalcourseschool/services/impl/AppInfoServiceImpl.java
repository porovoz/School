package ru.hogwarts.additionalcourseschool.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.hogwarts.additionalcourseschool.dto.AppInfoDTO;
import ru.hogwarts.additionalcourseschool.services.AppInfoService;

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
