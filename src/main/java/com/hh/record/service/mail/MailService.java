package com.hh.record.service.mail;

import com.hh.record.config.exception.errorCode.MailSendException;
import com.hh.record.security.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    private final RedisUtil redisUtil;

    public void sendMail(String email) {
        String certificationNum = MailUtils.getCertificationNum();
        MimeMessage message = getMessage(email, certificationNum);
        redisUtil.setDataExpire(certificationNum, email);
        javaMailSender.send(message);
    }

    public void checkCertification(String CertificationNum) {
        String email = redisUtil.getData(CertificationNum);
        if (email == null)
            throw new MailSendException("인증번호가 잘못되었거나 인증 시간이 초과되었습니다. 다시 확인해주세요.");
    }

    private MimeMessage getMessage(String email, String certificationNum) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("[hh-record] 본인 인증 메일");
            message.setText(getText(certificationNum), "UTF-8", "html");

            return message;
        } catch (Exception e) {
            throw new MailSendException("관리자에게 문의해주세요.");
        }
    }

    private String getText(String certificationNum) {
        String content = MailUtils.START_CONTENT +
                certificationNum +
                MailUtils.END_CONTENT;
        return content;
    }

}
