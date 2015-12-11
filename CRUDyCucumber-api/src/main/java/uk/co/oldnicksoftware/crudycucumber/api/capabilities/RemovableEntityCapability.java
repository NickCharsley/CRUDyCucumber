/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.api.capabilities;

/**
 *
 * @author nick
 */
public interface RemovableEntityCapability<Entity> {
    public void remove(Entity entity) throws Exception;
    public void removeAll() throws Exception;
}
