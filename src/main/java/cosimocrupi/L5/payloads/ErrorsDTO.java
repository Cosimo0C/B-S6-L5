package cosimocrupi.L5.payloads;

import java.time.LocalDateTime;

public record ErrorsDTO(String mess, LocalDateTime timeStamp) {
}
