package com.skynet.Util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final int SMTP_PORT = 587;
    private static final String EMAIL_FROM = "marroquinkenneth@gmail.com"; 
    private static final String EMAIL_PASSWORD = "41075039k"; 

    public static void enviarCorreo(String correoDestino, String asunto, String mensaje) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_FROM, EMAIL_PASSWORD);
            }
        });

        try {
            Message correo = new MimeMessage(session);
            correo.setFrom(new InternetAddress(EMAIL_FROM));
            correo.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestino));
            correo.setSubject(asunto);
            correo.setText(mensaje);
            Transport.send(correo);
            System.out.println("Correo enviado correctamente.");
        } catch (MessagingException e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}
