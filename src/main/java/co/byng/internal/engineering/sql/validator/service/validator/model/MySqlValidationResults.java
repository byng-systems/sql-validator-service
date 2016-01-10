/**
 * MySqlValidationResults.java
 * Created 10-Jan-2016 01:05:35
 *
 * @author M.D.Ward <matthew.ward@byng.co>
 * Copyright (c) 2016, Byng Services Ltd
 */

package co.byng.internal.engineering.sql.validator.service.validator.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Map;



/**
 * MySqlValidationResults
 * 
 * @author M.D.Ward <matthew.ward@byng.co>
 */
public class MySqlValidationResults extends ValidationResults {
    
    @Expose
    @SerializedName("named_parameters")
    public Map<String, Integer> namedParameters;
    
    @Expose
    @SerializedName("unnamed_parameters")
    public int unnamedParameters;
    
}
