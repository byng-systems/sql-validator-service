/*
 * Copyright (c) 2016, Byng Services Ltd
 */
package co.byng.internal.engineering.sql.validator.service.servlet;

import co.byng.internal.engineering.sql.validator.service.validator.MySqlValidator;
import co.byng.internal.engineering.sql.validator.service.validator.Validator;
import co.byng.internal.engineering.sql.validator.service.validator.factory.MySqlParserFactory;
import co.byng.internal.engineering.sql.validator.service.validator.model.MySqlValidationResults;
import co.byng.internal.engineering.sql.validator.service.validator.model.ValidationResults;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author M.D.Ward <matthew.ward@byng.co>
 */
public class MySqlValidatorServlet extends HttpServlet {

    private final Gson gson;
    private final Validator<MySqlValidationResults> validator;

    public MySqlValidatorServlet(Gson gson, Validator<MySqlValidationResults> validator) {
        this.gson = gson;
        this.validator = validator;
    }
    
    public MySqlValidatorServlet() {
        this(new Gson(), new MySqlValidator(new MySqlParserFactory()));
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("text/json;charset=UTF-8");
        
        try (final PrintWriter out = response.getWriter()) {
            String statement = request.getParameter("statement");

            ValidationResults results = this.validator.validate(statement, null);

            response.setStatus(
                results.validationState
                ? HttpServletResponse.SC_OK
                : HttpServletResponse.SC_BAD_REQUEST
            );

            out.println(this.gson.toJson(results));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Provides methods to validate a given SQL grammar";
    }

}
