<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>${paginaTitel}</title>
        <link href="<c:url value="/resources/style.css" />" rel="stylesheet" >
    </head>

    <body>
        <h2>${paginaTitel}</h2>
        <form:form method="POST" commandName="member" action="${pageContext.request.contextPath}/member/edit">

            <table>
                <tr>
                    <td>Id</td>
                    <td><form:input path="id" readonly="true" /></td>
                </tr>
                <tr>
                    <td>Voornaam</td>
                    <td><form:input path="firstName" /></td>

                </tr>
                <tr>
                    <td>Achternaam</td>
                    <td><form:input path="lastName" /></td>


                </tr>
                <tr>
                    <td>Team</td>
                    <td>
                        <form:select path="team">
                            <form:option value="${member.team.id}">${member.team.name}</form:option>
                            <form:options items="${teamList}" itemValue="id" itemLabel="name"/>
                        </form:select>
                    </td>

                </tr>
                <tr>
                    <td><input type="submit" value="Edit member" /></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>
