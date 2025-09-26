package communication;

import java.net.*;
import java.time.*;

public record NameSocket(String username, LocalDateTime joinTimeDate, Socket socket) {}
