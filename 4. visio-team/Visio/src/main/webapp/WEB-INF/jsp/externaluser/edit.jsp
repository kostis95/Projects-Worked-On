<%-- 
    Document   : beacons
    Created on : 15-nov-2016, 14:19:05
    Author     : Thomas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:include page="../header.jsp"></jsp:include> 
    <div class="container-fluid text-center">
        <div class="col-sm-6 col-sm-offset-3 col-md-8 col-md-offset-2 main">
            <h2>Edit external user</h2>
        <f:form class="form-horizontal" method="POST" modelAttribute="ExternalUser" commandName="ExternalUser" action="${pageContext.request.contextPath}/externaluser/edit">

            <f:input path="externalUserId" class="form-control" style="display: none;" value="${externalUser.externalUserId}" />
            <div class="form-group">
                <label class="control-label col-md-4" style ="text-align: left;" for="name">Name:</label>
                <div class="col-md-7">
                    <f:input path="name" class="form-control" value="${externalUser.name}" />
                    <f:errors path="name" />
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-4" style ="text-align: left;" for="birthdate">Birthdate:</label>
                <div class="col-md-7">
                    <f:input type="date" path="birthdate" class="form-control" value="${externalUser.birthdate}" />
                    <f:errors path="birthdate" />
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-4" style ="text-align: left;" for="gender">Geslacht:</label>
                <div class="col-md-7">
                    <f:select path="gender" class="form-control">
                        <f:options items="${genders}"></f:options>
                    </f:select>
                    <f:errors path="gender" />
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-4" style ="text-align: left;" for="VITId">User type:</label>
                <div class="col-md-7">
                    <f:select path="VITId" class="form-control">
                        <c:forEach var="vit" items="${VITs}">
                            <f:option value="${vit.VITId}">${vit.description}</f:option>
                        </c:forEach>
                    </f:select>
                    <f:errors path="VITId" />
                </div>
            </div>
            <f:input path="apiToken" class="form-control" value="${externalUser.apiToken}" style="display:none;" />


            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit" value="Save" class="btn btn-primary"/>
                </div>
            </div>
        </f:form>
    </div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>
