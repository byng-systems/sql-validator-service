/**
 * MySqlParserFactory.java
 * Created 10-Jan-2016 01:20:01
 *
 * @author M.D.Ward <matthew.ward@byng.co>
 * Copyright (c) 2016, Byng Services Ltd
 */

package co.byng.internal.engineering.sql.validator.service.validator.factory;

import org.antlr.grammarv4.mysql.MySQLLexer;
import org.antlr.grammarv4.mysql.MySQLParser;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRErrorStrategy;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;



/**
 * MySqlParserFactory
 * 
 * @author M.D.Ward <matthew.ward@byng.co>
 */
public class MySqlParserFactory implements ParserFactory<MySQLParser> {

    @Override
    public MySQLParser buildParser(String input, ANTLRErrorListener errorListener) {
        MySQLParser parser = new MySQLParser(
            new CommonTokenStream(
                new MySQLLexer(
                    new ANTLRInputStream(input)
                )
            )
        );
        
        parser.addErrorListener(errorListener);
        
        return parser;
    }
    
}
