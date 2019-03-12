package mapfood.repository.motoboy.impl;

import mapfood.model.motoboy.MotoboyStatus;
import mapfood.model.motoboy.MotoboyWithDistance;
import mapfood.repository.motoboy.MotoboyRepositoryCustom;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MotoboyRepositoryCustomImpl implements MotoboyRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public MotoboyRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<MotoboyWithDistance> findClosestMotoboy(GeoJsonPoint point) {
        List<AggregationOperation> list = new ArrayList<>();

        list.add(Aggregation.geoNear(NearQuery.near(point).spherical(true), "distance"));
        list.add(Aggregation.match(Criteria.where("motoboyStatus").is(MotoboyStatus.AVAILABLE)));
        list.add(Aggregation.sort(Sort.Direction.ASC, "distance"));
        list.add(Aggregation.limit(10));

        TypedAggregation<MotoboyWithDistance> agg = Aggregation.newAggregation(MotoboyWithDistance.class, list);
        return mongoTemplate.aggregate(agg, MotoboyWithDistance.class, MotoboyWithDistance.class).getMappedResults();
    }
}
