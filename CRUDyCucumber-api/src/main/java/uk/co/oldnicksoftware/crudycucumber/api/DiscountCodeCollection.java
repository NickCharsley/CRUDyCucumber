/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.api;

import java.util.List;
import uk.co.oldnicksoftware.crudycucumber.domain.DiscountCode;

/**
 *
 * @author nick
 */
public interface DiscountCodeCollection extends EntityCollection {
    public List getDiscountCodes();
    public DiscountCode getDiscountCode(DiscountCode search);    
}
