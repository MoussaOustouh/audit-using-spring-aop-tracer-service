package mo.spring.auditusingspringaoptracerservice.controllers;

import mo.spring.auditusingspringaoptracerservice.dto.TraceDTO;
import mo.spring.auditusingspringaoptracerservice.services.ITraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/traces")
public class TraceController {
    private final ITraceService traceService;

    @Autowired
    public TraceController(ITraceService traceService) {
        this.traceService = traceService;
    }


    @GetMapping()
    public List<TraceDTO> getAllTraces() {
        return traceService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<TraceDTO> getMemberById(@PathVariable(value = "id") Long traceId) {
        TraceDTO traceDTO = traceService.findById(traceId);
        return ResponseEntity.ok().body(traceDTO);
    }

    @PostMapping()
    public ResponseEntity<TraceDTO> createMember(@RequestBody TraceDTO traceDTO) {
        TraceDTO traceDTO1 = traceService.save(traceDTO);

        return ResponseEntity.ok(traceDTO1);
    }
}
