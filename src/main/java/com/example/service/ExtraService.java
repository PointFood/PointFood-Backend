package com.example.service;

import com.example.model.Extra;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ExtraService {

    Extra createExtra(Extra extra);
    Extra getExtraById(Long id);
    Extra updateExtra(Long id, Extra extra);
    ResponseEntity<?> deleteExtra(Long id);
    List<Extra> getExtrasByRestaurant(Long id);
}
