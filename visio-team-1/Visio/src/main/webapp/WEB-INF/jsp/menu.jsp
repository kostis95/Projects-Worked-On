<%-- 
    Document   : menuform
    Created on : Dec 15, 2013, 12:58:25 AM
    Author     : huub
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hoofdmenu</title>
        <img src="plaatjevisio.jpg" class="img-rounded" alt="Cinque Terre" width="304" height="236">
Try it Yourself »

    </head>

    <body>
        <h2>Dit is het Spring hoofdmenu, met verschillende lijsten</h2>
        <h3>${message}</h3>
        <table>
            <tr>
                <td><a href="${pageContext.request.contextPath}/member/memberlist">Ledenlijst</a></td>
            </tr>

            <tr>
                <td><a href="${pageContext.request.contextPath}/team/teamlist">Teamlijst</a></td>
            </tr>


        </table>

    </body>
</html>
