package TeamWork.project.service;


import TeamWork.project.dto.Rule;
import TeamWork.project.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleService {

    @Autowired
    private RuleRepository repository;

    public RuleService(RuleRepository repository) {
        this.repository = repository;
    }

    public Rule creat (Rule rule){
        return repository.save(rule);
    }

    public Rule delete(Rule rule) {
        return repository.delete();
    }

    public List<Rule> findAll(){
        return repository.findAll();
    }

}
