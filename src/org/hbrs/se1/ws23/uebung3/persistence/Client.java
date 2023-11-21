package org.hbrs.se1.ws23.uebung3.persistence;


import org.hbrs.se1.ws23.uebung2.ConcreteMember;
import org.hbrs.se1.ws23.uebung2.Exceptions.ContainerException;

public class Client {

    public static void main(String args[]) throws ContainerException {
        Container c  = Container.getInstance();
        c.addMember(new ConcreteMember(1));
        c.addMember(new ConcreteMember(2));
        c.addMember(new ConcreteMember(33));
        c.addMember(new ConcreteMember(52));

        new MemberView().dump(c.getCurrentList());


    }

}
