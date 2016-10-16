<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp" %>
<h1 style="margin-top:70px">${user.fullName}</h1>

<a class="btn btn-primary" href="addPost.html">Add new post
									»</a>





<table class="table table-bordered table-hover table-striped" style="margin-top:45px">
	
	<tbody>
		<c:forEach items="${user.posts}" var="post">
			<tr>
				<td>
				   <h1>${post.title}</h1>
				</td>
				<td>${post.content}</td>
			</tr>
		</c:forEach>
	</tbody>
</table> 



    
				
				
			
    
