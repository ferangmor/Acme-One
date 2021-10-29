package acme.features.anonymous.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.duties.Duty;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousDutyRespository extends AbstractRepository {

	
	@Query("select t from Duty t")
	Collection<Duty> findMany();
	
	@Query("select t from Duty t where t.isPublic = true and t.endTime > CURRENT_TIMESTAMP")
	Collection<Duty> findPublicNonFinishedDuties();
	
	@Query("select t from Duty t where t.id = ?1")
	Duty findDutyById(int id);
}