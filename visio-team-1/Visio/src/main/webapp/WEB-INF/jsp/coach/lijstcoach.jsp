<%-- 
    Document   : lijstcoach
    Created on : 2-nov-2016, 9:05:40
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Coach lijst</title>
    </head>
    <body>

        <h2>Dit is de Spring coachlijst van de Jeu de Boules Club</h2>
        <h3>${message}</h3>
        <table  border="1px" cellpadding="0" cellspacing="0">
            <tr>
                <td><strong> id</strong></td>
                <td><strong> Achternaam </strong></td>
                <td><strong> Coach niveau </strong></td>
                <td><strong> </strong></td>
            </tr>
            <c:forEach var="coach" items="${coaches}">
                <tr>
                    <td>${coach.id}</td>
                    <td>${coach.lastName}</td>
                    <td>${coach.levelOfCoaching}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/member/edit/${coach.id}">Edit</a>
                        <br/>
                        <a href="${pageContext.request.contextPath}/member/delete/${coach.id}">Delete</a><br/>
                    </td>
                </tr> 
            </c:forEach>
        </table>
        </form> <p>
            <a href="${pageContext.request.contextPath}/member/add">Maak nieuw lid aan</a>
        </p>
        <p>
            <a href="${pageContext.request.contextPath}/menu">Terug naar het menu</a>
        </p>
    </body>
</html>
