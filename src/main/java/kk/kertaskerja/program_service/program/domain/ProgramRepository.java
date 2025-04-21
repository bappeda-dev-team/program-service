package kk.kertaskerja.program_service.program.domain;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ProgramRepository extends ReactiveCrudRepository<Program, Long> {
    Mono<Boolean> existsByKodeProgram(String kodeProgram);
}
