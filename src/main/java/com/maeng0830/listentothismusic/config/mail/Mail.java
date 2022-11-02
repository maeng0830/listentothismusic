package com.maeng0830.listentothismusic.config.mail;

import com.maeng0830.listentothismusic.domain.Member;
import com.maeng0830.listentothismusic.exception.LimuException;
import com.maeng0830.listentothismusic.exception.errorcode.ErrorCode;
import com.maeng0830.listentothismusic.exception.errorcode.MemberErrorCode;
import com.maeng0830.listentothismusic.repository.MemberRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Mail {
    private final JavaMailSender javaMailSender;
    private final MemberRepository memberRepository;

    public void sendMail(String mailUrl, String subject, String text) {

        MimeMessagePreparator msg = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMessageHelper.setTo(mailUrl);
                mimeMessageHelper.setSubject(subject);
                mimeMessageHelper.setText(text, true);
            }
        };

        Optional<Member> optionalMember = memberRepository.findByEmail(mailUrl);

        if (!optionalMember.isPresent()) {
            throw new LimuException(MemberErrorCode.INCORRECT_MEMBER_EMAIL);
        } else {
            optionalMember.get().setSendAuthEmailDtt(LocalDateTime.now());
        }

        javaMailSender.send(msg);
    }
}
