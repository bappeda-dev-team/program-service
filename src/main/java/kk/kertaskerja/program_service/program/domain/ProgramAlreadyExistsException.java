package kk.kertaskerja.program_service.program.domain;

public class ProgramAlreadyExistsException extends RuntimeException {
    public ProgramAlreadyExistsException(String kodeProgram) {
      super("Program dengan kode " + kodeProgram + " sudah ada");
    }
}
