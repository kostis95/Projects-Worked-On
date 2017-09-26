<%-- 
    Document   : index
    Created on : Dec 14, 2013, 2:16:47 PM
    Author     : huub
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"></jsp:include>        
    <div class="col-sm-6 col-sm-offset-3 col-md-8 col-md-offset-2 main">
        <h1 class="page-header">Dashboard</h1>

        Welcome to the dashboard! <% request.getSession().getAttribute("name"); %>
       
    </div>
</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
