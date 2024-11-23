package org.example.myfriend.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/getImage")
public class GetImageServlet extends HttpServlet {


    private final String IMAGE_PATH = "/Users/raf/IdeaProjects/My-Friend/src/image/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String imgName = req.getParameter("imgName");


        if (imgName == null || imgName.trim().isEmpty()) {
            imgName = "default";
        }


        String[] possibleExtensions = {".jpg", ".jpeg", ".png"};
        File imgFile = null;

        for (String ext : possibleExtensions) {
            File file = new File(IMAGE_PATH + imgName + ext);
            if (file.exists()) {
                imgFile = file;
                break;
            }
        }


        if (imgFile == null) {
            imgFile = new File(IMAGE_PATH + "default.jpg");
        }

        if (!imgFile.exists()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found");
            return;
        }


        try (FileInputStream inputStream = new FileInputStream(imgFile)) {
            String mimeType = getServletContext().getMimeType(imgFile.getName());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            resp.setContentType(mimeType);
            resp.setContentLength((int) imgFile.length());
            OutputStream outputStream = resp.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error reading image");
        }
    }
}

