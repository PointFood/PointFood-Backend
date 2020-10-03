package com.example.service.Impl;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Extra;
import com.example.repository.ExtraRepository;
import com.example.service.ExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExtraServiceImpl implements ExtraService {

    @Autowired
    private ExtraRepository extraRepository;

    @Transactional
    @Override
    public Extra createExtra(Extra extra) {
        return extraRepository.save(extra);
    }

    @Transactional(readOnly = true)
    @Override
    public Extra getExtraById(Long id) {
        return extraRepository.findExtraById(id);
    }

    @Transactional
    @Override
    public Extra updateExtra(Long id, Extra extra) {
        Extra extraDB = extraRepository.getOne(id);

        extraDB.setName(extra.getName());
        extraDB.setDescription(extra.getDescription());
        extraDB.setPrice(extra.getPrice());

        return extraRepository.save(extraDB);
    }

    @Transactional
    @Override
    public ResponseEntity<?> deleteExtra(Long id) {
        Extra extraDB = extraRepository.getOne(id);
        if(extraDB == null){
            throw new ResourceNotFoundException("There is no extra with Id " + id);
        }
        extraRepository.delete(extraDB);

        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Extra> getExtrasByRestaurant(Long id) {
        return extraRepository.findExtrasByRestaurant(id);
    }
}
