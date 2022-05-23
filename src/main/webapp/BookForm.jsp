<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="./contents/headerTags.jsp" />   
        <title>Novo Livro</title>
    </head>
    <body>
        <div class="container">
            <jsp:include page="./contents/cabecalhoAdd.jsp" />

            <div align="left">
                <c:if test="${book != null}">
                    <form action="update" method="post">
                    </c:if>
                    <c:if test="${book == null}">
                        <form action="insert" method="post">
                        </c:if>
                        <table border="1" cellpadding="5">
                            <caption>
                                <h2>
                                    <c:if test="${book != null}">
                                        Editar Livro
                                    </c:if>
                                    <c:if test="${book == null}">
                                        Adicionar novo Livro
                                    </c:if>
                                </h2>
                            </caption>
                            <c:if test="${book != null}">
                                <input type="hidden" name="formId" value="<c:out
                                           value='${book.id}' />" />
                            </c:if>
                            <tr>
                                <th>Titulo: </th>
                                <td>
                                    <input type="text" name="formTitulo" size="45"
                                           value="<c:out value='${book.titulo}' />"
                                           />
                                </td>
                            </tr>
                            <tr>
                                <th>Autor: </th>
                                <td>
                                    <input type="text" name="formAutor" size="45"
                                           value="<c:out value='${book.autor}' />"
                                           />
                                </td>
                            </tr>
                            <tr>
                                <th>Preco: </th>
                                <td>
                                    <input type="text" name="formPreco" size="5"
                                           value="<c:out value='${book.preco}' />"
                                           />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center">
                                    <input type="submit" value="Enviar" />
                                </td>
                            </tr>
                        </table>  
                    </form>
                <jsp:include page="./contents/rodape.jsp" />
            </div>
        </div>
    </body>
</html>
