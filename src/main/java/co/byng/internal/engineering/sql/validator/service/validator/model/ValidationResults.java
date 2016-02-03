/**
 * ValidationResults.java
 * Created 10-Jan-2016 01:07:54
 *
 * @author M.D.Ward <matthew.ward@byng.co>
 * Copyright (c) 2016, Byng Services Ltd
 */

package co.byng.internal.engineering.sql.validator.service.validator.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.antlr.v4.runtime.tree.ParseTree;



/**
 * ValidationResults
 * 
 * @author M.D.Ward <matthew.ward@byng.co>
 */
public abstract class ValidationResults {

    @Expose
    @SerializedName("given_input")
    public String givenInput;

    @Expose
    @SerializedName("validation_state")
    public boolean validationState;

    @Expose
    @SerializedName("validation_errors")
    public String[] validationErrors;
    
}
