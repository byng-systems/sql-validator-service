/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.byng.internal.engineering.sql.validator.service.validator;

import java.util.Map;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * ParseTreeBuilder
 * 
 * @author M.D.Ward <matthew.ward@byng.co>
 * @copyright (c) 2016, Byng Services Ltd
 */
public interface ParseTreeBuilder {

    public ParseTree buildParseTree(String input, Map<String, String> additionalArguments);
    
}
