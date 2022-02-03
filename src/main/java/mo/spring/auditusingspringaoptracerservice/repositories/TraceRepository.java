package mo.spring.auditusingspringaoptracerservice.repositories;

import mo.spring.auditusingspringaoptracerservice.traceability.server.entities.Trace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TraceRepository extends JpaRepository<Trace, Long> {
    Optional<Trace> findTop1ByEntityIdAndEntityClassNameOrderByIdTraceDesc(Long entityId, String entityClassName);
    Optional<Trace> findTop1ByEntityIdAndEntityClassNameAndIdTraceNotOrderByIdTraceDesc(Long entityId, String entityClassName, Long idTrace);
}
