package com.equipeAcelera.EventifyAPI.services;

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
}
