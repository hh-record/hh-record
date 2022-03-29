package com.hh.record.service.record;

import com.hh.record.config.exception.errorCode.NotFoundException;
import com.hh.record.config.exception.errorCode.ValidationException;
import com.hh.record.entity.Theme;
import com.hh.record.repository.MemberFollowRepository;
import com.hh.record.repository.theme.ThemeRepository;

import java.time.LocalDateTime;

public class RecordServiceUtils {

    public static void validateFollow(MemberFollowRepository memberFollowRepository, Long memberId, Long targetMemberId) {
        boolean isFollow = memberFollowRepository.validateFollow(memberId, targetMemberId);
        if (!isFollow) {
            throw new ValidationException("follow를 하지 않은 사람의 일기는 볼 수 없습니다.");
        }
    }

    public static Theme findTheme(ThemeRepository themeRepository, String themeUse, LocalDateTime date) {
        if (themeUse.equals("N")) {
            return null;
        }
        return themeRepository.findDateByMonthAndDay(date.getMonthValue(), date.getDayOfMonth())
                .orElseThrow(() -> new NotFoundException("오늘의 주제가 존재하지 않습니다."));
    }

}
