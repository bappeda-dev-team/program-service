package kk.kertaskerja.program_service.program.domain;

public class ProgramNotFoundException extends RuntimeException {
    public ProgramNotFoundException(String kodeProgram) {
        super("Program dengan kode " + kodeProgram + " tidak ditemukan");
    }
}
