/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.byng.internal.engineering.sql.validator.service.ast.generator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * TreeViewerAstGenerator
 * 
 * @author M.D.Ward <matthew.ward@byng.co>
 * @copyright (c) 2016, Byng Services Ltd
 */
public class TreeViewerAstGenerator implements AstGenerator {

    protected final AstImageFactory<BufferedImage> imageFactory;
    protected final TreeViewerFactory treeViewerFactory;

    public TreeViewerAstGenerator(
        AstImageFactory<BufferedImage> imageFactory,
        TreeViewerFactory treeViewerFactory
    ) {
        this.imageFactory = imageFactory;
        this.treeViewerFactory = treeViewerFactory;
    }

    @Override
    public RenderedImage generateAst(ParseTree parseTree, String[] ruleNames) throws IOException {
        TreeViewer treeViewer = this.treeViewerFactory.buildTreeViewer(parseTree, ruleNames);
        
        Dimension bounds = treeViewer.getPreferredSize();
        BufferedImage image = this.imageFactory.buildImage(
            (int) bounds.getWidth(),
            (int) bounds.getHeight()
        );

        Graphics g = image.getGraphics();
        g.setColor(Color.BLACK);
        treeViewer.paint(g);

        return image;
    }

    @Override
    public RenderedImage generateAst(Parser parser, ParseTree parseTree) throws IOException {
        return this.generateAst(parseTree, parser.getRuleNames());
    }

}
