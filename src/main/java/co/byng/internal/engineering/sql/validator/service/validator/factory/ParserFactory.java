/**
 * ParserFactory.java
 * Created 10-Jan-2016 01:19:30
 *
 * @author M.D.Ward <matthew.ward@byng.co>
 * Copyright (c) 2016, Byng Services Ltd
 */

package co.byng.internal.engineering.sql.validator.service.validator.factory;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;



/**
 * ParserFactory 
 * 
 * @author M.D.Ward <matthew.ward@byng.co>
 */
public interface ParserFactory <P extends Parser> {

    public P buildParser(String input, ANTLRErrorListener errorListener);

}
