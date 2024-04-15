package elisadari.UN5W3d1praticaS3L1.payloads;

import java.time.LocalDateTime;
import java.util.List;

public record ListofErrorsDTO(String msg, LocalDateTime timeStamp, List<String>errors) {
}
