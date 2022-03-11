package com.hh.record.repository.theme;

import com.hh.record.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long>, ThemeCustomRepository {
}
