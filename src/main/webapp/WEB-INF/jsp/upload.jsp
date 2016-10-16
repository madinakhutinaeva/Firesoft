<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="<c:url value="/resources/js/jquery-2.1.4.min.js" />"></script>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <title>File Upload</title>
    <script type="text/javascript">
        $(document).ready(function () {
            $('progress').hide();
 
            var _href = $("#oldUploadLink").attr("href");
            $('#oldUploadLink').attr("href", _href + "?CKEditor=" + getUrlParameter("CKEditor"));
            $('.trigger-file-input').click(function () {
                $('#file').click();
            });
            $("input:file").change(function () {
 
                $('progress').show();
 
                var files = document.getElementById('file').files;
 
                var formData = new FormData();
                formData.append("${_csrf.parameterName}", "${_csrf.token}");
                formData.append("CKEditor", getUrlParameter("CKEditor"));
                formData.append("CKEditorFuncNum", getUrlParameter("CKEditorFuncNum"));
                formData.append("langCode", getUrlParameter("langCode"));
 
                for (var i = 0; i < files.length; i++) {
                    formData.append("file" + i, files[i]);
 
                }
                $.ajax({
                    url: "/Firesoftblog/upload-ajax",  //Server script to process data
                    type: 'POST',
                    xhr: function () {  // Custom XMLHttpRequest
                        var myXhr = $.ajaxSettings.xhr();
                        if (myXhr.upload) {
                            myXhr.upload.addEventListener('progress', progressHandlingFunction, false);
                        }
                        return myXhr;
                    },
                    //Ajax events
                    beforeSend: beforeSendHandler,
 
                    error: errorHandler,
                    // Form data
                    data: formData,
                    //Options to tell jQuery not to process data or worry about content-type.
                    cache: false,
                    contentType: false,
                    processData: false,
                    success: function (e) {
                        $("#result").html(e);
                    }
 
                });
            });
 
            function beforeSendHandler() {
 
            }
 
            function errorHandler(e) {
                alert("An error has occurred. Please try again");
            }
 
            function getUrlParameter(sParam) {
                var sPageURL = window.location.search.substring(1);
                var sURLVariables = sPageURL.split('&');
                for (var i = 0; i < sURLVariables.length; i++) {
                    var sParameterName = sURLVariables[i].split('=');
                    if (sParameterName[0] == sParam) {
                        return sParameterName[1];
                    }
                }
            }
 
            function progressHandlingFunction(e) {
                if (e.lengthComputable) {
                    $('progress').attr({value: e.loaded, max: e.total});
                }
            }
        });
    </script>
    <style type="text/css">
        ul li {
            list-style: none;
        }
    </style>
</head>
<body>
 
<div class=".b-modern-upload">
    <span>Choose files to be uploaded
        If you encounter problems please <a id="oldUploadLink" href="<c:url value="/uploadClassic.html"/>">click</a></span>
     
    <ul>
       
        <li>
            <input type="file" id="file" multiple name="file[]">
        </li>
        <li>
            <progress></progress>
        </li>
    </ul>
    <div id="result">
    </div>
</div>
</body>
</html>