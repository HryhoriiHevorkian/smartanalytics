package de.adorsys.smartanalytics.pers.mongo;

import de.adorsys.smartanalytics.pers.api.RuleEntity;
import de.adorsys.smartanalytics.pers.spi.RuleRepositoryIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Profile({"mongo-persistence", "fongo"})
@Service
public class RuleRepositoryImpl implements RuleRepositoryIf {

    @Autowired
    private RuleRepositoryMongodb ruleRepositoryMongodb;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public long count() {
        return ruleRepositoryMongodb.count();
    }

    @Override
    public void deleteAll() {
        ruleRepositoryMongodb.deleteAll();
    }

    @Override
    public void saveAll(List<RuleEntity> rules) {
        ruleRepositoryMongodb.saveAll(rules);
    }

    @Override
    public void save(RuleEntity ruleEntity) {
        ruleRepositoryMongodb.save(ruleEntity);
    }

    @Override
    public List<RuleEntity> findAll() {
        return ruleRepositoryMongodb.findAll();
    }

    @Override
    public void deleteById(String id) {
        ruleRepositoryMongodb.deleteById(id);
    }

    @Override
    public Page<RuleEntity> findAll(Pageable pageable) {
        return ruleRepositoryMongodb.findAll(pageable);
    }

    @Override
    public Optional<RuleEntity> getRuleByRuleId(String ruleId) {
        return ruleRepositoryMongodb.getRuleByRuleId(ruleId);
    }

    @Override
    public Iterable<RuleEntity> search(String search) {
        Collection<String> terms = new HashSet<>(Arrays.asList(search.split(" ")));

        Criteria[] criterias = terms
                .stream()
                .map(s -> Criteria.where("searchIndex").regex(s.toLowerCase(), "iu"))
                .toArray(Criteria[]::new);

        return mongoTemplate.find(Query.query(new Criteria().andOperator(criterias)), RuleEntity.class);
    }
}
