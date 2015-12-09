/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.api.capabilities;

/**
 *
 * @author nick
 * @param <Entity> The Entity Type
 */
public interface CreatableEntityCapability<Entity>{
    public void create(Entity entity) throws Exception;    
}
