package com.hh.record.service.member;

import com.hh.record.config.exception.errorCode.NotFoundException;
import com.hh.record.config.exception.errorCode.ValidationException;
import com.hh.record.dto.certification.CertificationRequestDTO;
import com.hh.record.dto.member.request.SelectMemberIdRequestDTO;
import com.hh.record.dto.member.request.UpdateMemberRequestDTO;
import com.hh.record.dto.member.response.MemberInfoResponse;
import com.hh.record.entity.Member;
import com.hh.record.dto.member.request.InsertMemberRequestDTO;
import com.hh.record.repository.member.MemberRepository;
import com.hh.record.security.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    @Transactional(readOnly = true)
    @Override
    public MemberInfoResponse selectMemberDTO(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 회원입니다."));
        return MemberInfoResponse.of(member);
    }

    @Transactional
    @Override
    public String loginMember(String id, String password) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 회원입니다."));
        MemberServiceUtils.validatePassword(passwordEncoder, password, member.getPassword());
        return jwtUtil.generateToken(member.getId(), member.getRoleSet());
    }

    @Transactional
    @Override
    public void updateMember(Long id, UpdateMemberRequestDTO requestDTO) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 회원입니다."));
        member.changeMemberInfo(requestDTO.getUserName(), requestDTO.getEmail(), requestDTO.getPhoneNumber());
    }

    @Transactional
    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void insertMember(InsertMemberRequestDTO request) {
        memberRepository.findById(request.getId())
                .ifPresent(member -> {
                    throw new ValidationException("이미 존재하는 아이디입니다.");
                });
        memberRepository.findByEmail(request.getEmail())
                .ifPresent(member -> {
                    throw new ValidationException("이미 존재하는 이메일입니다.");
                });
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        memberRepository.save(request.dtoToEntity(encodedPassword));
    }

    @Override
    public void checkMemberPassword(Long id, String password) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 회원입니다."));
        MemberServiceUtils.validatePassword(passwordEncoder, password, member.getPassword());
    }

    @Transactional
    @Override
    public void updateMemberPassword(Long id, String password) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 회원입니다."));
        MemberServiceUtils.validateSamePassword(passwordEncoder, password, member.getPassword());
        member.changePassword(passwordEncoder.encode(password));
    }

    @Override
    public String selectMemberId(SelectMemberIdRequestDTO request) {
        return memberRepository.findByInfo(request.getUserName(), request.getPhoneNumber(), request.getEmail());
    }

    @Override
    public void certificationMember(CertificationRequestDTO requestDTO) {
        memberRepository.findByIdAndEmail(requestDTO.getId(), requestDTO.getEmail())
                .orElseThrow(() -> new NotFoundException("회원 정보를 확인해주세요."));
    }

}
