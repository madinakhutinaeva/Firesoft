<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp" %>

<form:form commandName="user" cssClass="form-horizontal" cssStyle="margin-top:59px">

	<div class="form-group">
		<label for="name" class="col-sm-2 control-label">Name:</label>
		<div class="col-sm-10">
			<form:input path="fullName" cssClass="form-control" cssStyle="width: 700px"/>			
		</div>
	</div>
		<div class="form-group">
		<label for="name" class="col-sm-2 control-label">Username:</label>
		<div class="col-sm-10">
			<form:input path="username" cssClass="form-control" cssStyle="width: 700px"/>			
		</div>
	</div>
	<div class="form-group">
		<label for="email" class="col-sm-2 control-label">Email:</label>
		<div class="col-sm-10">
			<form:input path="email" cssClass="form-control" cssStyle="width: 700px" />			
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-2 control-label">Password:</label>
		<div class="col-sm-10">
			<form:password path="password" cssClass="form-control" cssStyle="width: 700px" />			
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-2 control-label">Password again:</label>
		<div class="col-sm-10">
			<input type="password" style="width: 700px" name="password_again" id="password_again"  class="form-control" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-2">
			<input type="submit" style="margin-left:150px" value="Save" class="btn btn-lg btn-primary" />
		</div>
	</div>
</form:form>
