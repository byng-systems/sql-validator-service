/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.byng.internal.engineering.sql.validator.service.ast.generator;

import java.awt.image.RenderedImage;
import java.io.IOException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;



/**
 * AstGenerator
 * 
 * @author M.D.Ward <matthew.ward@byng.co>
 * @copyright (c) 2016, Byng Services Ltd
 */
public interface AstGenerator {

    public RenderedImage generateAst(ParseTree parseTree, String[] ruleNames) throws IOException;
    
    public RenderedImage generateAst(Parser parser, ParseTree parseTree) throws IOException;

}
