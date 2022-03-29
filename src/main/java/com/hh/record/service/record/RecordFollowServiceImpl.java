package com.hh.record.service.record;

import com.hh.record.config.exception.errorCode.NotFoundException;
import com.hh.record.dto.record.RecordResponseDTO;
import com.hh.record.entity.Theme;
import com.hh.record.entity.record.IsPrivate;
import com.hh.record.entity.record.Record;
import com.hh.record.repository.MemberFollowRepository;
import com.hh.record.repository.record.RecordRepository;
import com.hh.record.repository.theme.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordFollowServiceImpl implements RecordFollowService {

    private final RecordRepository recordRepository;
    private final ThemeRepository themeRepository;
    private final MemberFollowRepository memberFollowRepository;

    @Transactional
    @Override
    public RecordResponseDTO selectOneRecord(Long memberId, Long recordId) {
        Record record = recordRepository.findByMember_SeqAndSeq(null, recordId)
                .orElseThrow(() -> new NotFoundException(String.format("존재하지 않는 일기 %s 입니다.", recordId)));
        RecordServiceUtils.validateFollow(memberFollowRepository, memberId, record.getMember().getSeq());
        LocalDateTime date = record.getRegDate();
        Theme theme = RecordServiceUtils.findTheme(themeRepository, record.getThemeUse(), date);
        return RecordResponseDTO.recordWithTheme(record, theme);
    }

    @Transactional
    @Override
    public List<RecordResponseDTO> retrieveRecord(Long memberId, Long followId) {
        RecordServiceUtils.validateFollow(memberFollowRepository, memberId, followId);
        return recordRepository.retrieveRecord(followId, null, null, null, Arrays.asList(IsPrivate.ALL_PUBLIC, IsPrivate.FRIEND_PUBLIC))
                .stream().map(RecordResponseDTO::of)
                .collect(Collectors.toList());
    }

}
