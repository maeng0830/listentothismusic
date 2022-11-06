package com.maeng0830.listentothismusic.config.mail;

import com.maeng0830.listentothismusic.exception.LimuException;
import com.maeng0830.listentothismusic.exception.errorcode.MemberErrorCode;
import com.maeng0830.listentothismusic.repository.MemberRepository;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateMail {
    private final JavaMailSender javaMailSender;
    private final MemberRepository memberRepository;

    public void sendMail(String mailAddress, String subject, String text) {

        // 전송될 메일에 해당하는 멤버가 있는지 확인
        memberRepository.findByEmail(mailAddress).orElseThrow(() -> new LimuException(MemberErrorCode.INCORRECT_MEMBER_EMAIL));

        MimeMessagePreparator msg = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMessageHelper.setTo(mailAddress);
                mimeMessageHelper.setSubject(subject);
                mimeMessageHelper.setText(text, true);
            }
        };

        javaMailSender.send(msg);
    }
}
