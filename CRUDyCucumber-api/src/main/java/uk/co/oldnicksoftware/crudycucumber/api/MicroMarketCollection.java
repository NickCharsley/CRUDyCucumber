/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.api;

import java.util.List;
import uk.co.oldnicksoftware.crudycucumber.domain.MicroMarket;

/**
 *
 * @author nick
 */
public interface MicroMarketCollection extends EntityCollection {
    public List getMicroMarkets();
    public MicroMarket getMicroMarket(MicroMarket search);    
}
