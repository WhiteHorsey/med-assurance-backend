package ma.medass.config;

import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import ma.medass.domain.AppConfig;
import ma.medass.repository.AppConfigRepository;

@Configuration
public class EmailConfiguration {
    @Autowired
    private AppConfigRepository appConfigRepository;

    @Bean
    public JavaMailSenderImpl javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        try {
            Properties props = new Properties();
            props.setProperty("mail.smtp.auth", getValue("mail.smtp.auth"));
            props.setProperty("mail.smtp.starttls.enable", getValue("mail.smtp.starttls.enable"));
            props.setProperty("mail.smtp.ssl.trust", getValue("mail.smtp.ssl.trust"));

            mailSender.setHost(getValue("mail.host"));
            mailSender.setPort(Integer.parseInt(getValue("mail.port")));
            mailSender.setUsername(getValue("mail.username"));
            mailSender.setPassword(getValue("mail.password"));
            mailSender.setProtocol(getValue("mail.protocol"));
            mailSender.setJavaMailProperties(props);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mailSender;
    }

    public String getValue(String key) throws Exception {
        Optional<AppConfig> appConfig = appConfigRepository.findByKey(key);
        if (appConfig.isPresent()) {
            return appConfig.get().getValue();
        }
        throw new Exception("mail param not found " + key);
    }
}
