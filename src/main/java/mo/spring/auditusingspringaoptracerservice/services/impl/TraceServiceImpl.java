package mo.spring.auditusingspringaoptracerservice.services.impl;

import mo.spring.auditusingspringaoptracerservice.dto.TraceDTO;
import mo.spring.auditusingspringaoptracerservice.dto.mapper.IMapper;
import mo.spring.auditusingspringaoptracerservice.traceability.server.entities.Trace;
import mo.spring.auditusingspringaoptracerservice.exceptions.NotFoundException;
import mo.spring.auditusingspringaoptracerservice.exceptions.constants.ErrorMessages;
import mo.spring.auditusingspringaoptracerservice.repositories.TraceRepository;
import mo.spring.auditusingspringaoptracerservice.services.ITraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TraceServiceImpl implements ITraceService {
    @Autowired
    private IMapper mapper;

    private final TraceRepository traceRepository;

    @Autowired
    public TraceServiceImpl(TraceRepository traceRepository) {
        this.traceRepository = traceRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public TraceDTO findById(Long id) {
        Optional<Trace> traceOptional = traceRepository.findById(id);
        if(traceOptional.isEmpty()){
            throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + " Trace { id : "+id+" }");
        }

        return mapper.map(traceOptional.get(), TraceDTO.class);
    }

    @Override
    public List<TraceDTO> findAll() {
        return mapper.mapList(traceRepository.findAll(), TraceDTO.class);
    }

    @Override
    public TraceDTO save(TraceDTO dto) {
        return mapper.map(
                traceRepository.save(mapper.map(dto, Trace.class)),
                TraceDTO.class
        );
    }
}