package org.hbrs.se1.ws23.uebung3.persistence;

import org.hbrs.se1.ws23.uebung2.ConcreteMember;
import org.hbrs.se1.ws23.uebung2.Exceptions.ContainerException;
import org.hbrs.se1.ws23.uebung2.Member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Container {

    private static Container INSTANCE;
    private ArrayList<Member> Members;
    public static PersistenceStrategy<Member> per;


    private Container() {
        Members = new ArrayList<>();
    }

    public static Container getInstance(){

        if(INSTANCE == null) {
            INSTANCE = new Container();
        }
        return INSTANCE;

    }

    public void addMember(ConcreteMember member) throws ContainerException {


        for (int i = 0; i < Members.size(); i++) {

            Member tmp = Members.get(i);
            if (member.getID() == Members.get(i).getID())
                throw new ContainerException("The id "+member.getID()+" already exists");


        }
        Members.add(member);
    }

    public String deleteMember(int id){

        for (int i = 0; i < Members.size(); i++) {

            Member tmp = Members.get(i);
            if (id == Members.get(i).getID()){
                Members.remove(i);
                return String.valueOf(id);
            }


        }
        return "The id "+id+" doenst exist";


    }

    public void store() throws PersistenceException {



        try {
            per.save(Members);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void load() throws PersistenceException {

        try {
            Members = (ArrayList<Member>) per.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
   public List<Member> getCurrentList(){
        return Members;

   }

    public int size(){

        return Members.size();

    }






}


