package kk.kertaskerja.program_service;

import kk.kertaskerja.program_service.common.HealthStatus;
import kk.kertaskerja.program_service.config.KertasKerjaProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class HomeController {
    private final KertasKerjaProperties kertasKerjaProperties;
    public HomeController(KertasKerjaProperties kertasKerjaProperties) {
        this.kertasKerjaProperties = kertasKerjaProperties;
    }

    @GetMapping("/")
    public HealthStatus healthStatus() {
        return new HealthStatus(
                kertasKerjaProperties.status(),
                Instant.now()
        );
    }
}
