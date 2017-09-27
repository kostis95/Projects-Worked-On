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
        <form:form method="POST" commandName="team" action="${pageContext.request.contextPath}/team/edit">

            <table>
                <tr>
                    <td>Id</td>
                    <td><form:input path="id" readonly="true" /></td>
                </tr>
                <tr>
                    <td>Naam</td>
                    <td><form:input path="name" /></td>

                </tr>
                <tr>
                    <td>Yell</td>
                    <td><form:input path="yell" /></td>


                </tr>

                <tr>
                    <td><input type="submit" value="Edit team" /></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>
