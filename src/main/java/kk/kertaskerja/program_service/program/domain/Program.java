package kk.kertaskerja.program_service.program.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;

public record Program(
        @Id Long id,
        String kodeProgram,
        String namaProgram,
        ProgramStatus status,

        @CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModifiedDate,

        @Version
        int version
) {
    public static Program of(String kodeProgram, String namaProgram, ProgramStatus status) {
        return new Program(null, kodeProgram, namaProgram, status, null, null, 0);
    }
}
