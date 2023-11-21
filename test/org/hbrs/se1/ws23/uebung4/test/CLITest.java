package org.hbrs.se1.ws23.uebung4.test;

import org.hbrs.se1.ws23.uebung4.prototype.improvements.controller.Container;
import org.hbrs.se1.ws23.uebung4.prototype.improvements.controller.ContainerException;
import org.hbrs.se1.ws23.uebung4.prototype.improvements.controller.UserStory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CLITest {
    static Container c;

    @BeforeAll
    static void setup() throws ContainerException {

        c = Container.getInstance();
        UserStory story1 = new UserStory();
        story1.setId(1);
        c.addUserStory(story1);
        c.store();

    }

    @Test
    void IDpositive() throws ContainerException {


        assertEquals(c.getCurrentList().size(), 1);

    }

    @Test
    void IDnegative() throws ContainerException {

        UserStory story1 = new UserStory();
        story1.setId(1);
        assertThrows(ContainerException.class, () -> c.addUserStory(story1));
    }

    @Test
    void CheckSave() throws Exception {
        c.load();
        assertEquals(1, c.size());

    }

}
