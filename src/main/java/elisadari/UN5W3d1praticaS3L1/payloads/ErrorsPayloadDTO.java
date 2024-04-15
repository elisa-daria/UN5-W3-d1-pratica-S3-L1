package elisadari.UN5W3d1praticaS3L1.payloads;

import java.time.LocalDateTime;

public record ErrorsPayloadDTO(String msg, LocalDateTime timeStamp) {
    public ErrorsPayloadDTO(String msg, LocalDateTime timeStamp) {
        this.msg = msg;
        this.timeStamp = timeStamp;
    }
}
