package org.hbrs.se1.ws23.uebung2;

import java.io.Serializable;

public class ConcreteMember implements Member, Serializable {
    private int id;
    public ConcreteMember(int id){

        this.id =id;

    }

    @Override
    public Integer getID() {
        return id;
    }

    public String toString(){

        return "Member (ID = "+String.valueOf(id)+")";

    }
}
