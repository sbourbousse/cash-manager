package epitech.backend.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class GlobalProperties {

    private String url;
    private String ftp;
    private String ftpUser;
    private String passwordFtp;
    private String captchaSecret;
    private String googleUri;

    public String getUrl() {
        return url;
    }

    public String getFtp() {
        return ftp;
    }

    public String getFtpUser() {
        return ftpUser;
    }

    public String getPasswordFtp() {
        return passwordFtp;
    }

    public final String getCaptchaSecret(){ return captchaSecret; }

    public final String getGoogleUri(){ return googleUri; }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFtp(String ftp) {
        this.ftp = ftp;
    }

    public void setFtpUser(String ftpUser) {
        this.ftpUser = ftpUser;
    }

    public void setPasswordFtp(String passwordFtp) {
        this.passwordFtp = passwordFtp;
    }

    public void setCaptchaSecret(String captchaSecret) { this.captchaSecret = captchaSecret; }

    public void setGoogleUri(String uri){ this.googleUri = uri; }

    @Override
    public String toString() {
        return "GlobalProperties{" +
                "url='" + url + '\'' +
                '}';
    }
}