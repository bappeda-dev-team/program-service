package kk.kertaskerja.program_service.common;

import java.time.Instant;

public record HealthStatus(
        String status,
        Instant timestamp
) {}