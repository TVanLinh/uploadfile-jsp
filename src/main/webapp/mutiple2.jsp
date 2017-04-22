<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head><title>Upload page</title></head></p> <p><body>
<fieldset>
    <legend align="center">Upload file</legend>
    <center>
        <form action="upload-multiple" method="post" enctype="multipart/form-data" name="form1" id="form1">
        <table border="2">
            <tr>
                <td align="center"><b>Multiple file Upload</b></td>
            </tr>
            <tr>
                <td>
                    Specify file: <input name="file" type="file" >
                <td>
            </tr>
            <tr>
                <td>
                    Specify file:<input name="file" type="file" >
                </td>
            <tr>
                <td>
                    Specify file:<input name="file" type="file" >
                </td>
            </tr>
            <tr>
                <td align="center">
                    <input type="submit" name="Submit" value="Submit files"/>
                </td>
            </tr>
        </table>
    </form>
    </center>
    <center>
    <%
        String str= (String) request.getAttribute("error");
        if(str!=null)
        {
            out.print("<b style='color:red'>" +str+"</b>");
        }

        List<String> list= (List<String>) request.getAttribute("listFile");
        if(list!=null)
        {
            for (String st:list)
            {
                out.println(st);
            }
        }
    %>
    </center>
</fieldset>
</body>
</html>