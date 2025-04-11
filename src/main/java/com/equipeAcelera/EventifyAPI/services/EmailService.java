package com.equipeAcelera.EventifyAPI.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private void sendEmail(String to, String subject, String htmlContent) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent);

            mailSender.send(message);
            System.out.println("Email enviado para " + to);

        } catch (MessagingException e) {
            System.err.println("Erro ao enviar email para " + to + ": " + e.getMessage());
        }
    }

    @Async
    public void sendWelcomeToNormal(String email, String userName) {
        String html = """
            <html>
                <body style='font-family: Arial, sans-serif;'>
                    <h2 style='color: #2e7d32;'>Olá, <strong>%s</strong>!</h2>
                    <p>Sua conta de <strong>usuário</strong> foi criada no Eventify.</p>
                    <p>Descubra eventos incríveis e aproveite!</p>
                    <p><a href='https://eventify.com/explore' style='color: #1e88e5;'>Explore eventos</a></p>
                    <p style='color: #757575;'>Atenciosamente,<br/>Equipe Eventify</p>
                </body>
            </html>
            """.formatted(userName);

        sendEmail(email, "🎉 Bem-vindo ao Eventify!", html);
    }

    @Async
    public void sendWelcomeToOrganizer(String email, String userName) {
        String html = """
            <html>
                <body style='font-family: Arial, sans-serif;'>
                    <h2 style='color: #6a1b9a;'>Olá, <strong>%s</strong>!</h2>
                    <p>Sua conta de <strong>organizador</strong> está pronta!</p>
                    <p>Comece a criar eventos e atrair participantes.</p>
                    <p><a href='https://eventify.com/organizer/dashboard' style='color: #1e88e5;'>Acesse seu painel</a></p>
                    <p style='color: #757575;'>Atenciosamente,<br/>Equipe Eventify</p>
                </body>
            </html>
            """.formatted(userName);

        sendEmail(email, "🚀 Bem-vindo, Organizador!", html);
    }
}