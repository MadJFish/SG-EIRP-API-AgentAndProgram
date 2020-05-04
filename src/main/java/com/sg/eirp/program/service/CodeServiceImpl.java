package com.sg.eirp.program.service;

import com.sg.eirp.program.model.Code;
import com.sg.eirp.program.repo.CodeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeRepo codeRepo;

    @Override
    public List<Code> getCodesByType(String type) {
        List<Code> resultList = new ArrayList<>();
        if (type == null || type.isEmpty()) {
            return resultList;
        }

        codeRepo.findByType(type).ifPresent(resultList::addAll);
        return resultList;
    }
}
