package edu.uiowa.webapp;

import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Schema extends ClayElement {

    Vector<Domain> domains = new Vector<Domain>();
    Vector<Entity> entities = new Vector<Entity>();
    Vector<Relationship> relationships = new Vector<Relationship>();
    
	private static final Log log = LogFactory.getLog(Schema.class);

	public boolean containsRelationship( Relationship relationship ){
		boolean contains = false;
		for(Relationship r : this.getRelationships()){
			if( r.sourceEntity != null && relationship.sourceEntity != null && r.targetEntity != null && relationship.targetEntity != null ){
				if( r.sourceEntity.lowerLabel != null && relationship.sourceEntity.lowerLabel != null && r.targetEntity.lowerLabel != null && relationship.targetEntity.lowerLabel != null ){
					if( r.sourceEntity.lowerLabel.equals(relationship.sourceEntity.lowerLabel) && r.targetEntity.lowerLabel.equals(relationship.targetEntity.lowerLabel) ){
						contains = true;
					}
				}
			}
		}
		return contains;
	}
	
    
    public Vector<Domain> getDomains() {
        return domains;
    }

    public void setDomains(Vector<Domain> domains) {
        this.domains = domains;
    }

    public Domain getDomainByLabel(String label) {
        Entity target = null;

        for (int i = 0; i < domains.size(); i++)
            if (domains.elementAt(i).getLabel().equals(label))
                return domains.elementAt(i);

        return null;
    }

    public Vector<Entity> getEntities() {
        return entities;
    }

    public void setEntities(Vector<Entity> entities) {
        this.entities = entities;
    }

    public Entity getEntityByLabel(String label) {
        Entity target = null;

        for (int i = 0; i < entities.size(); i++)
            if (entities.elementAt(i).getLabel().equals(label))
                return entities.elementAt(i);

        return null;
    }
    
    public Entity getEntityBySQLLabel(String label) {
        Entity target = null;

        for (int i = 0; i < entities.size(); i++)
            if (entities.elementAt(i).getSqlLabel().equals(label))
                return entities.elementAt(i);

        return null;
    }
    
    public  void populateEntityAttributeForeignReference()
    {
    	
    	
    	
    	Iterator<Entity> eIter = entities.iterator();
    	while(eIter.hasNext())
    	{
    		Entity e = eIter.next();
    		Iterator<Attribute> aIter = e.getAttributes().iterator();
    		while(aIter.hasNext())
    		{
    			Attribute a = aIter.next();
    			if(a.getReferencedEntityName()!=null)
    				a.setReferencedEntity(getEntityBySQLLabel(a.getReferencedEntityName()));
    			else
    				a.setReferencedEntity(e);
    			
    			a.setEntity(e);
    	
    			
    		}
    		
    		
    	}
	
    	
    	
    }

    public Vector<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(Vector<Relationship> relationships) {
        this.relationships = relationships;
    }

    public void relabel() {
        relabel(false);
        for (int i = 0; i < entities.size(); i++)
            entities.elementAt(i).relabel();
    }

    public void dump() {
        log.debug("\tschema: " + label + "\tuid: " + uid);
        for (int i = 0; i < entities.size(); i++)
            entities.elementAt(i).dump();
        for (int i = 0; i < relationships.size(); i++)
            relationships.elementAt(i).dump();
    }
}
