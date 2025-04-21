package kk.kertaskerja.program_service.bidang_urusan;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
public class BidangUrusanClient {
    private static final String BIDANG_URUSAN_ROOT_API = "/bidang_urusan/";
    private final WebClient webClient;

    public BidangUrusanClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<BidangUrusan> getByKodeBidangUrusan(String kodeBidangUrusan) {
        return webClient.get()
                .uri(BIDANG_URUSAN_ROOT_API + kodeBidangUrusan)
                .retrieve()
                .bodyToMono(BidangUrusan.class)
                .timeout(Duration.ofSeconds(3), Mono.empty())
                .onErrorResume(WebClientResponseException.NotFound.class,
                        e -> Mono.empty())
                .retryWhen(Retry.backoff(3, Duration.ofMillis(100)))
                .onErrorResume(Exception.class,
                        e -> Mono.empty());
    }
}
