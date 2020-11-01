package apap.tugas1.sipes.service;


import apap.tugas1.sipes.model.TipeModel;
import apap.tugas1.sipes.repository.TipeDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TipeServiceImpl implements TipeService {

    @Autowired
    TipeDb tipeDb;

    @Override
    public List<TipeModel> getListTipe() {
        return tipeDb.findAll();
    }
}
