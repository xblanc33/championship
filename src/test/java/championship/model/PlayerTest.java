package championship.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class PlayerTest {

    @Test
    public void contructorTest() {
        Player p1 = new Player("winner");
        assertEquals("winner", p1.getNickName());
        Player p2 = new Player("winner");
        assertEquals(p1, p2);
        try {
            Player p3 = new Player(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }
    }
}