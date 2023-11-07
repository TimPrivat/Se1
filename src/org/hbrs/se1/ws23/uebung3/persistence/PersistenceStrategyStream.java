package org.hbrs.se1.ws23.uebung3.persistence;

import org.hbrs.se1.ws23.uebung2.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    // URL of file, in which the objects are stored
    private String location = "objects.ser";
    private ObjectInputStream ois;
    private FileInputStream fis;
    private ObjectOutputStream oos;
    private FileOutputStream fos;

    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    /**
     * Method for opening the connection to a stream (here: Input- and Output-Stream)
     * In case of having problems while opening the streams, leave the code in methods load
     * and save.
     */

    public void openConnection() throws PersistenceException {

        try {
            fos = new FileOutputStream(location);
            oos = new ObjectOutputStream(fos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    /**
     * Method for closing the connections to a stream
     */
    public void closeConnection() throws PersistenceException {

        try {
            oos.close();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<E> member) throws PersistenceException {
        //openConnection();

        try {
            fos = new FileOutputStream(location);
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            oos.writeObject(member);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //  closeConnection();
        try {
            oos.flush();
            fos.close();
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<E> load() throws PersistenceException, IOException, ClassNotFoundException {

            fis = new FileInputStream(location);
            ois = new ObjectInputStream(fis);
            ArrayList<E> ret = (ArrayList) ois.readObject();


            if (ret instanceof List<?>) {
                List newListe = (List) ret;
                return newListe;
            }


            ois.close();
            fis.close();



        return null;
    }
}
