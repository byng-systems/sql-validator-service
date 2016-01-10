/**
 * Validator.java
 * Created 10-Jan-2016 01:10:09
 *
 * @author M.D.Ward <matthew.ward@byng.co>
 * Copyright (c) 2016, Byng Services Ltd
 */

package co.byng.internal.engineering.sql.validator.service.validator;

import co.byng.internal.engineering.sql.validator.service.validator.model.ValidationResults;
import java.util.Map;



/**
 * Validator 
 * 
 * @author M.D.Ward <matthew.ward@byng.co>
 */
public interface Validator <T extends ValidationResults> {

    
    public T validate(String input, Map<String, String> additionalArguments);
    
}
