package championship.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class ChampionshipTest {

    @Test
    public void constructorTest() {
        Championship champ = new Championship();
        assertEquals(new Player[0],champ.getPlayer());
        assertEquals(false, champ.isStarted());
        assertEquals(false, champ.isClosed());
    }

    @Test
    public void addPlayerTest() {
        Player winner = new Player("Winner");
        Player loser = new Player("loser");
        Player medium = new Player("medium");
        Championship champ = new Championship();
        champ.addPlayer(winner);
        champ.addPlayer(loser);
        champ.addPlayer(medium);
        assertEquals(3, champ.getPlayer().length);
    }

    @Test
    public void startTest() {
        Player winner = new Player("Winner");
        Player loser = new Player("loser");
        Player medium = new Player("medium");
        Championship champ = new Championship();
        champ.addPlayer(winner);
        champ.addPlayer(loser);
        champ.addPlayer(medium);
        champ.start();
        assertEquals(true, champ.isStarted());
    }

    @Test
    public void startMatchTest() {
        Player winner = new Player("Winner");
        Player loser = new Player("loser");
        Player medium = new Player("medium");
        Championship champ = new Championship();
        champ.addPlayer(winner);
        champ.addPlayer(loser);
        champ.addPlayer(medium);
        champ.start();
        champ.startMatch(winner, loser);
        champ.updateMatchScore(winner, loser, 3, 2);
        try {
            champ.startMatch(winner, loser);
            fail();
        } catch (ChampionshipException ex) {

        }
        try {
            champ.updateMatchScore(loser, winner, 2, 2);
            fail();
        } catch (ChampionshipException ex) {

        }
    }

    @Test
    public void closeMatchTest() {
        Player winner = new Player("Winner");
        Player loser = new Player("loser");
        Player medium = new Player("medium");
        Championship champ = new Championship();
        champ.addPlayer(winner);
        champ.addPlayer(loser);
        champ.addPlayer(medium);
        champ.start();
        champ.startMatch(winner, loser);
        champ.updateMatchScore(winner, loser, 3, 2);
        champ.closeMatch(winner, loser);
        try {
            champ.updateMatchScore(winner, loser, 3, 4);
            fail();
        } catch (ChampionshipException ex) {

        }
    }

    @Test
    public void mainScenario() {
        Player winner = new Player("Winner");
        Player loser = new Player("loser");
        Player medium = new Player("medium");

        Championship champ = new Championship();

        champ.addPlayer(winner);
        champ.addPlayer(loser);
        champ.addPlayer(medium);

        champ.start();

        champ.startMatch(winner, loser);
        champ.updateMatchScore(winner, loser, 4, 0);
        champ.closeMatch(winner, loser);

        champ.startMatch(loser, winner);
        champ.updateMatchScore(loser, winner, 3, 5);
        champ.closeMatch(loser, winner);

        champ.startMatch(winner, medium);
        champ.updateMatchScore(winner, medium, 4, 0);
        champ.closeMatch(winner, medium);

        champ.startMatch(medium, winner);
        champ.updateMatchScore(medium, winner, 3, 5);
        champ.closeMatch(medium, winner);

        champ.startMatch(loser, medium);
        champ.updateMatchScore(loser, medium, 4, 6);
        champ.closeMatch(loser, medium);

        champ.startMatch(medium, loser);
        champ.updateMatchScore(medium, loser, 5, 3);
        champ.closeMatch(medium, loser);

        Player[] obtained = champ.rankings();

        assertEquals(obtained[0], winner);
        assertEquals(obtained[1], medium);
        assertEquals(obtained[2], loser);
    }
}