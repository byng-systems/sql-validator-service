/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.byng.internal.engineering.sql.validator.service.validator.model;

import org.antlr.v4.runtime.tree.ParseTree;

/**
 * ParseTreeWrappingResults
 * 
 * @author M.D.Ward <matthew.ward@byng.co>
 * @copyright (c) 2016, Byng Services Ltd
 */
public class ParseTreeWrappingResults <V extends ValidationResults> extends ValidationResults {

    protected V results;
    protected ParseTree parseTree;

    public ParseTreeWrappingResults(V results, ParseTree parseTree) {
        this.results = results;
        this.parseTree = parseTree;
    }

    public V getResults() {
        return this.results;
    }

    public ParseTreeWrappingResults setResults(V results) {
        this.results = results;
        
        return this;
    }

    public ParseTree getParseTree() {
        return this.parseTree;
    }

    public ParseTreeWrappingResults setParseTree(ParseTree parseTree) {
        this.parseTree = parseTree;
        
        return this;
    }

}
