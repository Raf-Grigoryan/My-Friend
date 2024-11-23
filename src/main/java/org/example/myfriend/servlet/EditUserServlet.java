package org.example.myfriend.servlet;

import org.example.myfriend.model.User;
import org.example.myfriend.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet("/editUser")
@MultipartConfig
public class EditUserServlet extends HttpServlet {
    private final UserService userService = new UserService();

    private final String IMAGE_PATH = "/Users/raf/IdeaProjects/My-Friend/src/image";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/editUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/login");
            return;
        }

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String password = req.getParameter("password");
        Part imagePart = req.getPart("image");

        if (name == null || name.trim().isEmpty() || surname == null || surname.trim().isEmpty()) {
            req.setAttribute("error", "Name and surname are required.");
            req.getRequestDispatcher("/WEB-INF/editUser.jsp").forward(req, resp);
            return;
        }

        String imagePath = user.getImagePath();
        if (imagePart != null && imagePart.getSize() > 0) {
            try {
                File uploadDirFile = new File(IMAGE_PATH);
                if (!uploadDirFile.exists()) uploadDirFile.mkdir();

                String fileName = user.getId() + "_" + imagePart.getSubmittedFileName();
                imagePath = IMAGE_PATH + fileName;
                File file = new File(imagePath);
                imagePart.write(file.getAbsolutePath());
            } catch (IOException e) {
                req.setAttribute("error", "Failed to upload the image.");
                req.getRequestDispatcher("/WEB-INF/editUser.jsp").forward(req, resp);
                return;
            }
        }

        user.setName(name);
        user.setSurname(surname);
        if (password != null && !password.trim().isEmpty()) {
            user.setPassword(password);
        }
        user.setImagePath(imagePath);

        if (userService.updateUser(user)) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/profile");
        } else {
            req.setAttribute("error", "Failed to update the profile.");
            req.getRequestDispatcher("/WEB-INF/editUser.jsp").forward(req, resp);
        }
    }
}