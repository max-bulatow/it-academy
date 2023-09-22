package by.itacademy.web.decoder.impl;

import by.itacademy.web.decoder.Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;

import static it.academy.core.util.Constants.DEFAULT_CHARSET;


public class InputStreamDecoder implements Decoder {
    private final HttpServletRequest request;

    public InputStreamDecoder(final HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public InputStream getDecodedStream() throws IOException {
        try (final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream(), DEFAULT_CHARSET))) {

            final var content = bufferedReader.lines()
                    .reduce("", String::concat);
            final var jsonContent = content.split("=")[1];

            final var json = URLDecoder.decode(jsonContent, DEFAULT_CHARSET);
            return new ByteArrayInputStream(json.getBytes(DEFAULT_CHARSET));
        }
    }
}
