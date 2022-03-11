package com.hh.record.repository.theme;

import com.hh.record.entity.Theme;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.hh.record.entity.QTheme.*;

@RequiredArgsConstructor
public class ThemeCustomRepositoryImpl implements ThemeCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Theme> selectByDate(int month, int day) {
        return Optional.ofNullable(
                queryFactory.selectFrom(theme)
                        .where(
                                theme.month.eq(month),
                                theme.day.eq(day)
                        ).fetchOne()
        );
    }

}
