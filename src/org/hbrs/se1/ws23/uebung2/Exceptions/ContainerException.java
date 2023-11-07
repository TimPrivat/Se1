package org.hbrs.se1.ws23.uebung2.Exceptions;

public class ContainerException extends Exception{
    public ContainerException(){

        super("The ID already exists in this Container");

    }
    public ContainerException (String message) {
        super (message);
    }


}
