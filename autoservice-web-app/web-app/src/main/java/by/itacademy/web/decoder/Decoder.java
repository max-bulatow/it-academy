package by.itacademy.web.decoder;

import java.io.IOException;
import java.io.InputStream;

public interface Decoder {
    InputStream getDecodedStream() throws IOException;
}
