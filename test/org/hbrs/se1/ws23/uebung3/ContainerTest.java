package org.hbrs.se1.ws23.uebung3;

import org.hbrs.se1.ws23.uebung2.ConcreteMember;
import org.hbrs.se1.ws23.uebung2.Exceptions.ContainerException;
import org.hbrs.se1.ws23.uebung2.Member;
import org.hbrs.se1.ws23.uebung3.persistence.Container;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class ContainerTest {

    @Test
    public void StrategyIsNull() {

        Container c = Container.getInstance();
        assertEquals(null, c.per);

    }

    @Test
    public void useMongoDBStore() {
        Container c = Container.getInstance();
        c.per = new PersistenceStrategyMongoDB<>();
        assertThrows(UnsupportedOperationException.class, () -> c.store());

    }
    @Test
    public void useMongoDBLoad() {
        Container c = Container.getInstance();
        c.per = new PersistenceStrategyMongoDB<>();
        assertThrows(UnsupportedOperationException.class, () -> c.load());

    }

    @Test
    public void wrongFile() {

        Container c = Container.getInstance();
        PersistenceStrategyStream<Member> pers = new PersistenceStrategyStream<>();
        pers.setLocation("C:\\Users\\timle\\Documents");
        assertThrows(FileNotFoundException.class, () -> pers.load());

    }

    @Test
    public void RoundTrip() throws ContainerException, PersistenceException {

        Container c = Container.getInstance();

        for (int i = 0; i < 10; i++) {

            c.addMember(new ConcreteMember(i));

        }
        c.per = new PersistenceStrategyStream<>();
        c.store();
        assertEquals(10, c.size());
        for (int i = 0; i < 10; i++) {

            c.deleteMember(i);

        }


        c.load();
        assertEquals(10, c.size());


    }
}
