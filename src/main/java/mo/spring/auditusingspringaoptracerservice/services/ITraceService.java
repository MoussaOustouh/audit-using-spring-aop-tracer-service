package mo.spring.auditusingspringaoptracerservice.services;

import mo.spring.auditusingspringaoptracerservice.dto.TraceDTO;

import java.util.List;

public interface ITraceService {
    TraceDTO findById(Long id);

    List<TraceDTO> findAll();

    TraceDTO save(TraceDTO dto);
}
