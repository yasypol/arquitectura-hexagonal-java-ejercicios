package com.astrobookings.fleet.domain.models.vos;

import com.astrobookings.shared.domain.models.ValidationException;

public class RocketId {

    private String id;
    
    public RocketId(String id) throws ValidationException {

        if (id == null || id.trim().isEmpty()) {
            throw new ValidationException("Rocket ID must be provided");
        }
        
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
}
