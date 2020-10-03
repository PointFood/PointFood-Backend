package com.example.service.Impl;

import com.example.model.State;
import com.example.repository.StateRepository;
import com.example.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StateServiceImpl implements StateService {

    @Autowired
    private StateRepository stateRepository;

    @Transactional(readOnly = true)
    @Override
    public List<State> getStates() {
        return stateRepository.findAll();
    }
}
