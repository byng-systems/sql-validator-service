/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.byng.internal.engineering.sql.validator.service.servlet;

import co.byng.internal.engineering.sql.validator.service.ast.generator.AstGenerator;
import co.byng.internal.engineering.sql.validator.service.ast.generator.AstImageFactory;
import co.byng.internal.engineering.sql.validator.service.ast.generator.TreeViewerAstGenerator;
import co.byng.internal.engineering.sql.validator.service.ast.generator.TreeViewerFactory;
import co.byng.internal.engineering.sql.validator.service.ast.streamer.AstImageStreamer;
import co.byng.internal.engineering.sql.validator.service.ast.streamer.ImageIoAstStreamer;
import co.byng.internal.engineering.sql.validator.service.validator.MySqlValidator;
import co.byng.internal.engineering.sql.validator.service.validator.ParseTreeBuilder;
import co.byng.internal.engineering.sql.validator.service.validator.factory.MySqlParserFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.antlr.grammarv4.mysql.MySQLParser.ruleNames;

/**
 * MySqlAstImageServlet
 * 
 * @author M.D.Ward <matthew.ward@byng.co>
 * @copyright (c) 2016, Byng Services Ltd
 */
public class MySqlAstImageServlet extends HttpServlet {

    protected final ParseTreeBuilder parseTreeBuilder;
    protected final AstGenerator astGenerator;
    protected final AstImageStreamer astImageStreamer;

    public MySqlAstImageServlet(
        ParseTreeBuilder parseTreeBuilder,
        AstGenerator astGenerator,
        AstImageStreamer astImageStreamer
    ) {
        this.parseTreeBuilder = parseTreeBuilder;
        this.astGenerator = astGenerator;
        this.astImageStreamer = astImageStreamer;
    }

    public MySqlAstImageServlet() {
        this(
            new MySqlValidator(new MySqlParserFactory()),
            new TreeViewerAstGenerator(
                new AstImageFactory.BufferedImpl(),
                new TreeViewerFactory.Impl()
            ),
            new ImageIoAstStreamer.DefaultImpl()
        );
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String statement = req.getParameter("statement");
        String format = "png";

        resp.setHeader("Content-Type", "image/" + format);

        this.astImageStreamer.streamImage(
            this.astGenerator.generateAst(
                this.parseTreeBuilder.buildParseTree(
                    statement,
                    null),
                ruleNames
            ),
            format,
            resp.getOutputStream()
        );
    }

}
