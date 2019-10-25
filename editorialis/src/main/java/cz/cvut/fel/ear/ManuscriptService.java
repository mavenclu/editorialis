package cz.cvut.fel.ear;

import cz.cvut.fel.ear.dao.ManuscriptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManuscriptService {
    private ManuscriptRepository manuscriptRepository;
    @Autowired
    public ManuscriptService(ManuscriptRepository repository){
       manuscriptRepository = repository;
    }

    public int numberOfNewManuscripts(){
        return 0;
    }
}
