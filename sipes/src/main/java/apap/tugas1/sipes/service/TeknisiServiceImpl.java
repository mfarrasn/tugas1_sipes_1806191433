package apap.tugas1.sipes.service;


import apap.tugas1.sipes.model.TeknisiModel;
import apap.tugas1.sipes.repository.TeknisiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional

public class TeknisiServiceImpl implements TeknisiService {

    @Autowired
    TeknisiDb teknisiDb;

    @Override
    public List<TeknisiModel> getListTeknisi() {
        return teknisiDb.findAll();
    }
}
