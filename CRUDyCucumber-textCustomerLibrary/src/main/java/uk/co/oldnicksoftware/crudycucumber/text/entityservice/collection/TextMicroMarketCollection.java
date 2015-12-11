/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.text.entityservice.collection;

import java.util.List;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import uk.co.oldnicksoftware.crudycucumber.api.MicroMarketCollection;
import uk.co.oldnicksoftware.crudycucumber.domain.MicroMarket;

/**
 *
 * @author nick
 */
@ServiceProvider(service = MicroMarketCollection.class)
public class TextMicroMarketCollection extends TextCollection implements MicroMarketCollection{
    public TextMicroMarketCollection(){
        // Add a "Reloadable" ability to this entity:
        // ... and a "Savable" ability:
        // ...and a "Creatable" ability:
        // ... and a "Removeable" ability:
    }
    
    @Override
    public List getMicroMarkets() {
        return collection;
    }

    @Override
    public MicroMarket getMicroMarket(MicroMarket search) {
        return search;
    }    
}
