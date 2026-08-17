package ru.prplhd.tasktracker.emailsender.message;

public record EmailSendingTask(
        String recipient,
        String subject,
        String text
) {
}
