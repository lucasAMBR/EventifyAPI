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
            helper.setText(htmlContent, true);

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
                    <p style='color: #757575;'>Atenciosamente,<br/>Equipe Eventify</p>
                </body>
            </html>
            """.formatted(userName);

        sendEmail(email, "🚀 Bem-vindo, Organizador!", html);
    }

    @Async
    public void sendSubscriptionConfirmation(String email, String userName) {
        String html = """
            <html>
                <body style='font-family: Arial, sans-serif;'>
                    <h2 style='color: #6a1b9a;'>Olá, <strong>%s</strong>!</h2>
                    <p>Sua participação no evento está confirmada!</p>
                    <p style='color: #757575;'>Atenciosamente,<br/>Equipe Eventify</p>
                </body>
            </html>
            """.formatted(userName);

        sendEmail(email, "🚀 Sua inscrição foi confirmada!", html);
    }

    @Async
    public void sendEventFullNotification(String organizerEmail, String organizerName,
                                          String eventTitle, int guestLimit, String participantsList) {
        String html = """
        <html>
            <body style='font-family: Arial, sans-serif;'>
                <h2 style='color: #d32f2f;'>Parabéns, <strong>%s</strong>!</h2>
                <p>Seu evento <strong>%s</strong> atingiu o limite máximo de <strong>%d</strong> participantes!</p>
                
                <h3>Lista de Participantes:</h3>
                %s
                
                <p style='color: #757575; margin-top: 20px;'>Atenciosamente,<br/>Equipe Eventify</p>
            </body>
        </html>
        """.formatted(organizerName, eventTitle, guestLimit, participantsList);

        sendEmail(organizerEmail, "🎉 Evento lotado: " + eventTitle, html);
    }

    @Async
    public void sendResetCodeEmail(String email, String userName, String code) {
        String html = """
    <html>
        <body style='font-family: Arial, sans-serif;'>
            <h2 style='color: #2e7d32;'>Olá, <strong>%s</strong>!</h2>
            <p>Você solicitou uma redefinição de senha no Eventify.</p>
            <p>Seu código de verificação é: <strong>%s</strong></p>
            <p>Este código expira em 15 minutos.</p>
            <p style='color: #757575;'>Atenciosamente,<br/>Equipe Eventify</p>
        </body>
    </html>
    """.formatted(userName, code);

        sendEmail(email, "🔑 Código de verificação - Eventify", html);
    }
}