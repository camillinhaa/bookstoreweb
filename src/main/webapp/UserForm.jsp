<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="./contents/headerTags.jsp" />   
        <title>Cadastro</title>
    </head>
    <body>
        <h1>Cadastro</h1>
        <form action="<%=request.getContextPath()%>/bsuser/register" method="post">
            <table>
                <tr>
                    <td>
                        <label for="userName"><strong>Nome</strong></label>
                        <input type="text" name="userName" value="<c:out value='${pojoUser.fullname}'/>"> 
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="userEmail"><strong>Email</strong></label>
                        <input type="text" name="userEmail" value="<c:out value='${pojoUser.email}'/>"> 
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="userPassword"><strong>Password</strong></label>
                        <input type="password" name="userPassword"value="<c:out value='${pojoUser.password}'/>"> 
                    </td>
                </tr>
               
            </table>
            <br>
             <input type="submit" value="enviar">
        </form>
    </body>
</html>
