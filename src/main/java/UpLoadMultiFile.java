import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by linhtran on 22/04/2017.
 */
@WebServlet(name = "UpLoadMultiFile",urlPatterns = "/upload-multiple")
public class UpLoadMultiFile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req,resp);
    }

    private void action(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isMutipart= ServletFileUpload.isMultipartContent(req);
        System.out.println(isMutipart);
        if(isMutipart)
        {
            FileItemFactory fileItemFactory=new DiskFileItemFactory();

            ServletFileUpload servletFileUpload=new ServletFileUpload(fileItemFactory);

            List items=null;
            try {
                items=servletFileUpload.parseRequest(req);
            }catch (FileUploadException e)
            {
                Logger.getLogger(this.getClass().getName()).finest("Upload file error");
                req.setAttribute("error","Not upload file");
            }

            Iterator iterator=items.iterator();
            FileItem item=null;

            // create thu muc upload
            String path=this.getServletContext().getRealPath("/")+"upload/";
            System.out.println("path: "+path);
            File file =new File(path);
            List<String> list=new ArrayList<String>();

            if(!file.isDirectory()||!file.exists())
            {
                System.out.println(file.isDirectory());
                System.out.println(file.exists());
                file.mkdir();
            }
            while (iterator.hasNext())
            {
                item=(FileItem)iterator.next();

                if(item.isFormField())
                {

                }
                else
                {
                    try {
                            String itemName=item.getName();
                            if(itemName!=null&&!itemName.trim().equals(""))
                            {
                                System.out.println("equals: "+itemName.trim().equals(""));
                                File file1=new File(path+itemName);

                                item.write(file1);
                                list.add(itemName);

                            }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            req.setAttribute("listFile",list);
        }

        req.getRequestDispatcher("/mutiple2.jsp").forward(req,resp);
    }

}
