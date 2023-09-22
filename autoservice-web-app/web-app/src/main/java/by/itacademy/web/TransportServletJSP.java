package by.itacademy.web;

import by.itacademy.model.Transport;
import by.itacademy.web.decoder.Decoder;
import by.itacademy.web.decoder.impl.InputStreamDecoder;
import it.academy.core.parser.TransportParser;
import it.academy.core.parser.TransportParserException;
import it.academy.core.parser.impl.TransportJsonParser;
import it.academy.core.reader.TransportReader;
import it.academy.core.reader.TransportReaderException;
import it.academy.core.reader.impl.TransportJsonReader;
import it.academy.core.sorter.SorterReaderException;
import it.academy.core.sorter.SortingReader;
import it.academy.core.sorter.impl.SorterReader;
import it.academy.core.transport.TransportContainer;
import it.academy.core.validator.FieldValidatorException;
import it.academy.core.validator.Validator;
import it.academy.core.validator.impl.TransportValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static it.academy.core.util.Constants.DEFAULT_CHARSET;

public class TransportServletJSP extends HttpServlet {
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final TransportParser parser = new TransportJsonParser();
        final Decoder decoder = new InputStreamDecoder(request);
        final TransportReader reader = new TransportJsonReader(decoder.getDecodedStream(), parser);
        final Validator validator = new TransportValidator();
        TransportContainer transportContainer;

        response.setContentType("text/html");
        response.setCharacterEncoding(DEFAULT_CHARSET.name());

        try {
            final List<Transport> transportList = reader.read();
            transportContainer = validator.validateTransport(transportList);
            final String sorting = request.getParameter("sorting");

            if (sorting != null) {
                final SortingReader sorter = new SorterReader();
                sorter.readSorting(sorting, transportContainer);
            }
        } catch (final TransportReaderException | TransportParserException |
                       FieldValidatorException | SorterReaderException exception) {
            throw new IOException("Введено неправильное значение сортировки", exception);
        }

        request.setAttribute("validTransport", transportContainer.getValidTransport());
        request.setAttribute("invalidTransport", transportContainer.getInvalidTransport());

        request.getRequestDispatcher("/table.jsp").forward(request, response);
    }
}