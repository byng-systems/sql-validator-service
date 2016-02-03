/**
 * MySqlValidator.java
 * Created 10-Jan-2016 01:05:17
 *
 * @author M.D.Ward <matthew.ward@byng.co>
 * Copyright (c) 2016, Byng Services Ltd
 */

package co.byng.internal.engineering.sql.validator.service.validator;

import co.byng.internal.engineering.sql.validator.service.validator.factory.MySqlParserFactory;
import co.byng.internal.engineering.sql.validator.service.validator.factory.ParserFactory;
import co.byng.internal.engineering.sql.validator.service.validator.model.MySqlValidationResults;
import co.byng.internal.engineering.sql.validator.service.validator.model.ParseTreeWrappingResults;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import org.antlr.grammarv4.mysql.MySQLParser;
import org.antlr.grammarv4.mysql.MySQLParserBaseVisitor;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;



/**
 * MySqlValidator
 * 
 * @author M.D.Ward <matthew.ward@byng.co>
 */
public class MySqlValidator implements Validator<MySqlValidationResults>, ParseTreeBuilder {

    private final ParserFactory<MySQLParser> parserFactory;

    public MySqlValidator(MySqlParserFactory parserFactory) {
        this.parserFactory = parserFactory;
    }

    @Override
    public ParseTree buildParseTree(
        final String input,
        final Map<String, String> additionalArguments
    ) {
        return this.doValidate(input).getParseTree();
    }
    
    @Override
    public MySqlValidationResults validate(
        final String input,
        final Map<String, String> additionalArguments
    ) {
        return this.doValidate(input).getResults();
    }
    
    protected ParseTreeWrappingResults<MySqlValidationResults> doValidate(String input) {
        final List<String> errors = new ArrayList<>();
        
        MySQLParser parser = this.parserFactory.buildParser(
            input,
            new BaseErrorListener() {

                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                    errors.add(msg);
                }
            }
        );
        
        final Map<String, Integer> namedParameters = new LinkedHashMap<>();
        final List<Boolean> unnamedParameters = new ArrayList<>();
        
        ParseTree parseTree;
        new MySQLParserBaseVisitor() {

            @Override
            public Object visitNamed_parameter(MySQLParser.Named_parameterContext ctx) {
                String id = ctx.ID().getText();
                
                namedParameters.put(
                    id,
                    (
                        namedParameters.containsKey(id)
                        ? namedParameters.get(id) + 1
                        : 1
                    )
                );
                
                return super.visitNamed_parameter(ctx);
            }

            @Override
            public Object visitUnnamed_parameter(MySQLParser.Unnamed_parameterContext ctx) {
                unnamedParameters.add(Boolean.TRUE);
                
                return super.visitUnnamed_parameter(ctx);
            }
            
        }.visit((parseTree = parser.stat()));
        
        MySqlValidationResults result = new MySqlValidationResults();

        result.givenInput = input;
        
        result.namedParameters = namedParameters;
        result.unnamedParameters = unnamedParameters.size();
        
        result.validationState = errors.isEmpty();
        result.validationErrors = errors.toArray(new String[errors.size()]);

        return new ParseTreeWrappingResults<>(result, parseTree);
    }
    
}
