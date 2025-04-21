package kk.kertaskerja.program_service.program.web;

import jakarta.validation.Valid;
import kk.kertaskerja.program_service.program.domain.Program;
import kk.kertaskerja.program_service.program.domain.ProgramService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("program")
public class ProgramController {
    private final ProgramService programService;
    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping
    public Flux<Program> getAllPrograms() {
        return programService.getAllPrograms();
    }

    @GetMapping("{kodeProgram}")
    public Mono<Program> getByKodeProgram(@PathVariable String kodeProgram) {
        return programService.getByKodeProgram(kodeProgram);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Program> addProgram(@RequestBody @Valid ProgramRequest request) {
        return programService
                .addProgram(request.kodeBidangUrusan(),
                        request.kodeProgram(),
                        request.namaProgram());
    }
}
