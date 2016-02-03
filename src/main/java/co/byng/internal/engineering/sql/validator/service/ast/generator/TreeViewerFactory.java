/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.byng.internal.engineering.sql.validator.service.ast.generator;

import java.util.Arrays;
import java.util.List;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * TreeViewerFactory
 * 
 * @author M.D.Ward <matthew.ward@byng.co>
 * @copyright (c) 2016, Byng Services Ltd
 */
public interface TreeViewerFactory {

    public TreeViewer buildTreeViewer(ParseTree parseTree, String[] ruleNames);
    
    public TreeViewer buildTreeViewer(ParseTree parseTree, List<String> ruleNames);
    
    
    
    public static class Impl implements TreeViewerFactory {

        @Override
        public TreeViewer buildTreeViewer(ParseTree parseTree, List<String> ruleNames) {
            return new TreeViewer((List<String>) ruleNames, parseTree);
        }

        @Override
        public TreeViewer buildTreeViewer(ParseTree parseTree, String[] ruleNames) {
            return this.buildTreeViewer(parseTree, Arrays.asList(ruleNames));
        }
        
    }
    
}
