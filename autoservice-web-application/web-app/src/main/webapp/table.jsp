<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Autoservice-web-application</h2>
   <html>
        <body>
            <table border="1px">
                <caption><h2>Processed transport</h2></caption>
                <tr>
                  <th>Type</th>
                  <th>Model</th>
                  <th>Cost</th>
                </tr>
                   <c:forEach var = "transport" items = "${validTransport}">
                        <tr>
                            <td>${transport.transportType}</td>
                            <td>${transport.model}</td>
                            <td>${transport.transportType.getPrice()}</td>
                        </tr>
                   </c:forEach>
            </table>

            <table border="1px">
                <caption><h2>Invalid transport</h2></caption>
                <tr>
                  <th>Type</th>
                  <th>Model</th>
                </tr>
                   <c:forEach var = "transport" items = "${invalidTransport}">
                        <tr>
                            <td>${transport.transportType}</td>
                            <td>${transport.model}</td>
                        </tr>
                   </c:forEach>
            </table>
        </body>
   </html>