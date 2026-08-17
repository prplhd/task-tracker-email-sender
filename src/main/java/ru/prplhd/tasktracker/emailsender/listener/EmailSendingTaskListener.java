package ru.prplhd.tasktracker.emailsender.listener;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.prplhd.tasktracker.emailsender.message.EmailSendingTask;
import ru.prplhd.tasktracker.emailsender.service.EmailSendingService;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailSendingTaskListener {

    private static final String EMAIL_SENDING_TOPIC_NAME = "EMAIL_SENDING_TASKS";

    private final EmailSendingService emailSendingService;

    @KafkaListener(topics = EMAIL_SENDING_TOPIC_NAME)
    public void handle(EmailSendingTask task) {
        log.info("Received email sending task for {}. Subject: {}", task.recipient(), task.subject());

        emailSendingService.send(task);
    }
}
