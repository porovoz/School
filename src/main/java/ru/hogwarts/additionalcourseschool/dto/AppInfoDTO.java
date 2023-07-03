package ru.hogwarts.additionalcourseschool.dto;

public class AppInfoDTO {
    private String appName;
    private String appVersion;
    private String appEnvironment;

    public AppInfoDTO() {
    }

    public AppInfoDTO(String appName, String appVersion, String appEnvironment) {
        this.appName = appName;
        this.appVersion = appVersion;
        this.appEnvironment = appEnvironment;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppEnvironment() {
        return appEnvironment;
    }

    public void setAppEnvironment(String appEnvironment) {
        this.appEnvironment = appEnvironment;
    }

    @Override
    public String toString() {
        return "AppInfoDTO{" +
                "appName='" + appName + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", appEnvironment='" + appEnvironment + '\'' +
                '}';
    }
}
