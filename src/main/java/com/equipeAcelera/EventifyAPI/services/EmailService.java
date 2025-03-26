package com.equipeAcelera.EventifyAPI.services;

import com.equipeAcelera.EventifyAPI.models.Event.Event;
import com.equipeAcelera.EventifyAPI.models.Subscription.Subscription;
import com.equipeAcelera.EventifyAPI.models.User.NormalUser;
import com.equipeAcelera.EventifyAPI.models.User.OrganizerUser;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;


    public void sendHtmlEmail(String destinatario, String assunto, String html) throws MessagingException {
        try {

            MimeMessage mensagem = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, true, "UTF-8");

            helper.setTo(destinatario);
            helper.setSubject(assunto);
            helper.setText(html, true);
            helper.setFrom("EquipeEventfy@gmail.com");

            mailSender.send(mensagem);
            System.out.println("E-mail enviado para: " + destinatario);

        } catch (MessagingException e) {
            System.err.println("Falha ao enviar e-mail para " + destinatario + ": " + e.getMessage());
            throw e;
        }
    }

    public void sendNormalWelcomeEmail(NormalUser user) throws MessagingException {
        String html = """
        <html>
            <body>
                <h2>Bem-vindo ao Eventify!</h2>
                <p>Olá %s,</p>
                <p>Sua conta foi criada com sucesso.</p>
                <p>Agora você pode se inscrever nos melhores eventos!</p>
            </body>
        </html>
        """.formatted(user.getName());

        sendHtmlEmail(user.getEmail(), "Cadastro de usuário confirmado", html);
    }

    public void sendOrganizerWelcomeEmail(OrganizerUser organizer) throws MessagingException {
        String html = """
        <html>
            <body>
                <h2>Bem-vindo ao Eventify, Organizador!</h2>
                <p>Olá %s,</p>
                <p>Sua conta de organizador foi criada com sucesso.</p>
                <p>Agora você pode criar e gerenciar seus eventos!</p>
            </body>
        </html>
        """.formatted(organizer.getName(), organizer.getContact());

        sendHtmlEmail(organizer.getEmail(), "Cadastro de organizador confirmado", html);
    }

    public void sendSubscriptionEmail(NormalUser findedUser, Subscription newSub, Event findedEvent) throws MessagingException {
        String html = """
        <html>
            <body>
                <h2>Inscrição Confirmada</h2>
                <p>Olá %s, sua inscrição no evento %s foi confirmada.</p>
                <p>Data: %s</p>
                <p>Número da inscrição: %d</p>
            </body>
        </html>
        """.formatted(
                findedUser.getName(),
                findedEvent.getTitle(),
                findedEvent.getDate() != null ? findedEvent.getDate().toString() : "A definir",
                newSub.getId()
        );

        sendHtmlEmail(findedUser.getEmail(), "Inscrição em evento confirmada", html);
    }

}
