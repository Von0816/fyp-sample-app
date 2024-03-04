package com.example.messagingservice.service;


import jakarta.activation.MimetypesFileTypeMap;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private static final String LOGO_URL = "https://test-afroinsight-cdn-storage.b-cdn.net/logo.png";
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;


    public void  sendSongCommentEmail(String senderName, String songTitle, String comment, String recipientEmail) throws MessagingException {

        Context context= new Context();
        context.setVariable("name",senderName);
        context.setVariable("songTitle",songTitle);
        context.setVariable("comment",comment);
        context.setVariable("logo","logo.png");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true,"UTF-8");
        message.setSubject("New Comment "+songTitle);
        message.setFrom("no-reply@afroinsight.com");
        message.setTo(recipientEmail);

        String processed = templateEngine.process("song-comment-email-template", context);
        message.setText(processed,true);
        MimetypesFileTypeMap mimetypesFileTypeMap= new MimetypesFileTypeMap();
        byte[] logoBytes = getLogoBytes();
        String contentType = mimetypesFileTypeMap.getContentType("logo.png");
        InputStreamSource logoSource = new ByteArrayResource(logoBytes);
        message.addInline("logo.png",logoSource,contentType);
        javaMailSender.send(mimeMessage);
    }

    private byte[] getLogoBytes() {

        try {
            URL url= new URL(LOGO_URL);
            InputStream inputStream = url.openStream();
            return IOUtils.toByteArray(inputStream);

        } catch (Exception e) {
           log.error("EmailService LOGOEROOR getLogoBytes()",e);
        }

        return new byte[0];
    }


}
