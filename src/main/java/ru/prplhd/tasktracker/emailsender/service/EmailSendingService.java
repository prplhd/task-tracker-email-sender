package ru.prplhd.tasktracker.emailsender.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.prplhd.tasktracker.emailsender.message.EmailSendingTask;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailSendingService {

    private final JavaMailSender mailSender;

    @Value("${application.mail.from}")
    private String from;

    public void send(EmailSendingTask task) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(task.recipient());
        message.setSubject(task.subject());
        message.setText(task.text());

        try {
            mailSender.send(message);
        } catch (MailException e) {
            log.error("Failed to send email to {}", task.recipient(), e);
        }
    }
}
