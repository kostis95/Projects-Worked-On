
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>TeamMemberslijst</title>
    </head>
    <body>

        <h2>Dit is de Spring teamMembers lijst van de Jeu de Boules Club</h2>
        <h3>${message}</h3>
        <table  border="1px" cellpadding="0" cellspacing="0">
            <tr>

                <td><strong>${team.name}</strong></td>
            </tr>
            <tr>
                <td><strong>${team.yell}</strong></td>
            </tr>
        </table>
        <p>
            <table>
                <tr>
                    <td>Voornaam</td>
                    <td>Achternaam</td>
                </tr>

                <c:forEach var="member" items="${teamMembers}">
                    <tr>
                        <td>${member.firstName}</td>
                        <td>${member.lastName}</td>

                        <td>

                            <a href="${pageContext.request.contextPath}/member/deleteTeam/${member.id}">Delete member from team</a><br/>
                        </td>
                    </tr> 
                </c:forEach>
            </table>
        </p>

        <p>
            <a href="${pageContext.request.contextPath}/menu">Terug naar het menu</a>
        </p>
    </body>
</html>
