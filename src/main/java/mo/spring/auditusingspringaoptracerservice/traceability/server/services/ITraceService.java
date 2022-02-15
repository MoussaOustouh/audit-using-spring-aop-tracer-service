package mo.spring.auditusingspringaoptracerservice.traceability.server.services;

import mo.spring.auditusingspringaoptracerservice.traceability.server.dto.TraceDTO;

import java.util.List;

public interface ITraceService {
    TraceDTO findById(Long id);

    List<TraceDTO> findAll();

    TraceDTO save(TraceDTO dto);
}
