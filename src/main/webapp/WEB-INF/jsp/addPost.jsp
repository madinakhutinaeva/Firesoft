<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <script src="<c:url value="/resources/js/jquery-2.1.4.min.js" />"></script>
 <script charset=UTF-8 src="<c:url value="/ckeditor/ckeditor.js" />"></script>
 
  <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css">
  

<script type="text/javascript">
 $(document).ready(
		 function(){
			 
			 $.getJSON('<spring:url value="themes.json"/>', {
				 ajax : 'true'
			 }, function(data){
				 var html = '<option value="">--Please select theme of posts--</option>';
				 var len = data.length;
				 for (var i = 0; i < len; i++) {
					 html += '<option value="'+data[i].desc + '">'
					 +data[i].desc + '</option>';
				 }
				 html +='</option>';
				 $('#themes').html(html);
			 });
		 });
	  
</script>
<style type="text/css">


.error {
color: #ff0000;
}
  
  
.errorblock {
color: #000;
background-color: #ffeeee;
border: 3px solid #ff0000;
padding: 8px;
margin: 16px;

}  
  label { padding: 0; marign: 0; display: block; }
  #themes {width: 30em; height: 2em; margin-bottom: 20px}
  textarea { width:60em; height: 40em; border: 1px solid #333; padding: 4px}
  #title {width: 60em; border: 1px solid #333; padding: 4px;top-margin:10px}
  html, body {
    background: #eeeeee !important;
    font-family: Helvetica, Arial, Sans !important;
}

body {
    width: 900px;
    padding: 0 30px;
    margin: auto;
    text-align: center;
}

.blank_row
{
    height: 20px ;
    
}


 </style>
</head>
<body>
<h1 style="margin-top:100px">Create Post</h1>

Language: 
   <a href="?language=en">
        English
      </a> |
      <a  href="?language=ru">
        Russian
      </a>
      <br>
      <br />

 
<form:form commandName="post">
  <form:errors path="*" cssClass="errorblock" element="div" />
           <form:select id="themes" path="themes"></form:select>
           <br />
           <br />
           
            <spring:message code="post.text" />
            <br />
            <form:input path="title"/>
            <form:errors path="title" cssClass="error" element="div"/>
       
    
 <br /> <br />
 
        <spring:message code="post.cont" />
        <br />
         <form:textarea name='edit' id='edit' path="content"/> 
        
        <form:errors path="content" cssClass="error" element="div"/>
       
        <br />

       <center> <input id="submit" type="submit" value="Create" /> </center>
       <div>
          
       </div>
       
       
</form:form>



<ckeditor:replace replace='edit' basePath="/Firesoftblog/ckeditor/" />



</body>
</html>