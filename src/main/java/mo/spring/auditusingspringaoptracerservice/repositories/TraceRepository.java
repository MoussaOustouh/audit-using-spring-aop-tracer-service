package mo.spring.auditusingspringaoptracerservice.repositories;

import mo.spring.auditusingspringaoptracerservice.entities.Trace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraceRepository extends JpaRepository<Trace, Long> {
}
