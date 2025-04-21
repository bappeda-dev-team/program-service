package kk.kertaskerja.program_service.program.domain;

import kk.kertaskerja.program_service.bidang_urusan.BidangUrusanClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProgramService {
    private final BidangUrusanClient bidangUrusanClient;
    private final ProgramRepository programRepository;
    public ProgramService(BidangUrusanClient bidangUrusanCLient, ProgramRepository programRepository) {
        this.bidangUrusanClient = bidangUrusanCLient;
        this.programRepository = programRepository;
    }

    public Flux<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    public Mono<Program> getByKodeProgram(String kodeProgram) {
        return programRepository.existsByKodeProgram(kodeProgram)
                .flatMap( exists -> {
                    if (!exists) return Mono.error(new ProgramNotFoundException(kodeProgram));
                    return programRepository.findByKodeProgram(kodeProgram);
                });
    }

    public Mono<Program> addProgram(String kodeBidangUrusan, String kodeProgram, String namaProgram) {
        return programRepository.existsByKodeProgram(kodeProgram)
                .flatMap(exists -> {
                    if (exists) return Mono.error(new ProgramAlreadyExistsException(kodeProgram));
                    return bidangUrusanClient.getByKodeBidangUrusan(kodeBidangUrusan)
                            .map(bidUr -> buildProgramValid(kodeProgram, namaProgram))
                            .defaultIfEmpty(buildProgramTidakValid(kodeProgram, namaProgram));
                })
                .flatMap(programRepository::save);
    }

    public static Program buildProgramValid(String kodeProgram, String namaProgram) {
        return Program.of(kodeProgram, namaProgram, ProgramStatus.VALID);
    }

    public static Program buildProgramTidakValid(String kodeProgram, String namaProgram) {
        return Program.of(kodeProgram, namaProgram, ProgramStatus.TIDAK_VALID);
    }
}
