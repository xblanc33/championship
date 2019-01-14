package championship.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ChampionshipTest {

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
        assertEquals(obtained[1], loser);
        assertEquals(obtained[2], medium);
    }
}