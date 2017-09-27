
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Ledenlijst per team</title>
    </head>
    <body>

        <h2>Dit is de Spring leden lijst per team, van de Jeu de Boules Club</h2>
        <h3>${message}</h3>
        <h3>Voor een overzicht van de leden per team klik op de naam van het team!</h3>
        <table  border="1px" cellpadding="0" cellspacing="0">
            <tr>
                <td><strong>id</strong></td>
                <td><strong>Naam</strong></td>
                <td><strong>Yell</strong></td>
                <td><strong></strong></td>
            </tr>
            <c:forEach var="team" items="${teams}">
                <tr>
                    <td>${team.id}</td>
                    <td><a href="${pageContext.request.contextPath}/team/teamMembers/${team.id}">${team.name}</a></td>
                    <td>${team.yell}</td>

                    <td>

                        <a href="${pageContext.request.contextPath}/team/delete/${team.id}">Delete Team
                        </a>
                    </td>
                </tr> 
            </c:forEach>
        </table>
        <p>
            <a href="${pageContext.request.contextPath}/menu">Terug naar het menu</a>
        </p>
    </body>
</html>
