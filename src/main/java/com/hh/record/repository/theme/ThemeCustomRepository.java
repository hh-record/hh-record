package com.hh.record.repository.theme;

import com.hh.record.entity.Theme;

import java.util.Optional;

public interface ThemeCustomRepository {

    Optional<Theme> selectByDate(int month, int day);

}
