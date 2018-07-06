package de.adorsys.smartanalytics.pers.mongo;

import de.adorsys.smartanalytics.pers.api.CategoriesContainerEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile({"mongo", "fongo"})
public interface BookingCategoriesRepositoryMongodb extends MongoRepository<CategoriesContainerEntity, String> {
}
