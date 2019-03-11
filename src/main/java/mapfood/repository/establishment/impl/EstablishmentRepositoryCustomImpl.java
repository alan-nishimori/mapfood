package mapfood.repository.establishment.impl;

import mapfood.model.establishment.EstablishmentWithDistance;
import mapfood.repository.establishment.EstablishmentRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.NearQuery;

import java.util.ArrayList;
import java.util.List;

public class EstablishmentRepositoryCustomImpl implements EstablishmentRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public EstablishmentRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<EstablishmentWithDistance> findEstablishmentsNextToClient(GeoJsonPoint point) {
        List<AggregationOperation> list = new ArrayList<>();
        Distance distance = new Distance(1, Metrics.KILOMETERS);

        list.add(Aggregation.geoNear(NearQuery.near(point).spherical(true).maxDistance(distance), "distance"));
        list.add(Aggregation.sort(Sort.Direction.ASC, "distance", "name"));

        TypedAggregation<EstablishmentWithDistance> agg = Aggregation.newAggregation(EstablishmentWithDistance.class, list);
        return mongoTemplate.aggregate(agg, EstablishmentWithDistance.class, EstablishmentWithDistance.class).getMappedResults();
    }
}
