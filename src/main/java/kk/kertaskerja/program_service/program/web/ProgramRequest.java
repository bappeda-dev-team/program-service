package kk.kertaskerja.program_service.program.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ProgramRequest(
        @NotBlank(message = "Kode bidang urusan wajib terisi")
        String kodeBidangUrusan,

        @NotBlank(message = "Kode program wajib terisi")
        @Pattern(
                regexp = "^([A-Z]|\\d)\\.([A-Z]{2}|\\d{2})\\.(\\d{2})$",
                message = "Format kode tidak valid"
        )
        String kodeProgram,

        @NotBlank(message = "Nama program wajib terisi")
        String namaProgram
) {
}
