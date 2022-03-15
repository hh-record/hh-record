package com.hh.record.repository.theme;

import com.hh.record.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThemeRepository extends JpaRepository<Theme, Long>, ThemeCustomRepository {

    Optional<Theme> findDateByMonthAndDay(int month, int day);

}
