package com.example.kartonpredmeta.service;

import com.example.kartonpredmeta.dto.ThematicUnitDTO;
import java.util.List;

public interface ThematicUnitService {
    List<ThematicUnitDTO> findByCourse(Long id);
}
