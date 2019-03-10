package mapfood.repository;

import mapfood.model.MotoboyStatus;
import mapfood.model.MotoboyWithDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;

import java.util.ArrayList;
import java.util.List;

public class MotoboyRepositoryImpl implements MotoboyRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MotoboyRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public MotoboyWithDistance findClosestMotoboy(GeoJsonPoint point) {
        List<AggregationOperation> list = new ArrayList<>();

        list.add(Aggregation.geoNear(NearQuery.near(point).spherical(true), "distance"));
        list.add(Aggregation.match(Criteria.where("status").is(MotoboyStatus.AVAILABLE)));
        list.add(Aggregation.sort(Sort.Direction.ASC, "distance"));
        list.add(Aggregation.limit(1));

        TypedAggregation<MotoboyWithDistance> agg = Aggregation.newAggregation(MotoboyWithDistance.class, list);
        return mongoTemplate.aggregate(agg, MotoboyWithDistance.class, MotoboyWithDistance.class).getUniqueMappedResult();
    }
}
