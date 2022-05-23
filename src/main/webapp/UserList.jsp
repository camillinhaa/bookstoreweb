<<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <body>
        <div class="container">
                    <c:forEach var="book" items="${listaBook}">

                        <tr>
                            <td><c:out value="${book.id}" /></td>
                            <td><c:out value="${book.titulo}" /></td>
                            <td><c:out value="${book.autor}" /></td>
                            <td><c:out value="${book.preco}" /></td>
                            <td>
                                <a href="<%=request.getContextPath()%>/edit?id=<c:out value='${book.id}' />"
                                   <span class="glyphicon glyphicon-pencil"></span>
                                </a> &nbsp;&nbsp;&nbsp;&nbsp; 
                                <a href="<%=request.getContextPath()%>/delete?id=<c:out value='${book.id}'/>">
                                    <span class="glyphicon glyphicon-trash"></span>
                                </a>
                            </td>
                        </tr>

                    </c:forEach>
                </table>
                <jsp:include page="./contents/rodape.jsp" />
            </div>
        </div>
    </body>
</html>
