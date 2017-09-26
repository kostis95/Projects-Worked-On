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
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

        <div class="col-md-12 text-left">
            <div class="col-md-6 text-left">
                <div class="container-fluid">
                    <h2>Add Vision Impaired Type</h2>
                <f:form class="form-horizontal" method="POST" modelAttribute="VIT" commandName="VIT" action="${pageContext.request.contextPath}/vits/add">
                    <div class="form-group">
                        <label class="col-md-4 control-label" style ="text-align: left;">Description</label>
                        <div class="col-md-7">
                            <f:input path="description" cssClass="form-control" />
                            <f:errors path="description" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-4 col-md-8">
                            <button type="submit" class="btn btn-primary">Add</button>
                        </div>
                    </div>
                </f:form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>
