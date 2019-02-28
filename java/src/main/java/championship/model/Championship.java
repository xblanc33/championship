package championship.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Championship {
    public static final int DRAW = 1;
    public static final int WIN = 3;

    private Set<Player> playerSet;
    private Set<Match> matchSet;
    private Map<Player, Match> startedMatch;
    private boolean isStarted;

    public Championship() {
        playerSet = new HashSet<Player>();
        matchSet = new HashSet<Match>();
        startedMatch = new HashMap<Player, Match>();
        isStarted = false;
    }

    public void addPlayer(Player player) {
        if (isStarted) {
            throw new ChampionshipException("Championship is closed");
        }
        playerSet.add(player);
    }

    public Player[] getPlayer() {
        return playerSet.toArray(new Player[playerSet.size()]);
    }

    public boolean isClosed() {
        if (!isStarted && matchSet.size() == 0) {
            return false;
        }
        for (Match match : matchSet) {
            if (!match.isClosed()) {
                return false;
            }
        }
        return true;
    }

    public void start() {
        if (isStarted) {
            throw new ChampionshipException("Championship started");
        }
        isStarted = true;
        makeAllMatch();
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void startMatch(Player player1, Player player2) {
        boolean player1AlreadyPlays = startedMatch.containsKey(player1);
        boolean player2AlreadyPlays = startedMatch.containsKey(player2);
        if (player1AlreadyPlays || player2AlreadyPlays) {
            throw new ChampionshipException("A player is playing a match");
        }
        Match match = findMatch(player1, player2);
        match.start();
        startedMatch.put(player1, match);
        startedMatch.put(player2, match);
    }

    public void closeMatch(Player player1, Player player2) {
        Match match = findMatch(player1, player2);
        match.close();
        startedMatch.remove(player1);
        startedMatch.remove(player2);
    }

    public void updateMatchScore(Player p1, Player p2, int s1, int s2) {
        Match match = findMatch(p1, p2);
        match.updateScorePlayer1(s1);
        match.updateScorePlayer1(s2);
    }

    public Player[] rankings() {
        if (!isClosed()) {
            throw new ChampionshipException("not closed");
        }
        Map<Player, Integer> playerScore = new HashMap<>();
        for (Match match : matchSet) {
            Player[] players = match.getTwoPlayers();
            if (!playerScore.containsKey(players[0])) {
                playerScore.put(players[0], new Integer(0));
            }
            if (!playerScore.containsKey(players[1])) {
                playerScore.put(players[1], new Integer(0));
            }
            if (match.isDraw()) {
                int s1 = playerScore.get(players[0]).intValue() + DRAW;
                playerScore.put(players[0], new Integer(s1));
                int s2 = playerScore.get(players[1]).intValue() + DRAW;
                playerScore.put(players[1], new Integer(s2));
            } else {
                Player winner = match.getWinner();
                int s1 = playerScore.get(winner).intValue() + WIN;
                playerScore.put(winner, new Integer(s1));
            }
        }
        return playerScore
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .map(entry -> {
                    return entry.getKey();
                })
                .toArray(Player[]::new);
    }

    private void makeAllMatch() {
        for (Player player1 : playerSet) {
            for (Player player2 : playerSet) {
                if (player1 != player2) {
                    Match match = new Match(player1, player2);
                    matchSet.add(match);
                }
            }
        }
    }

    private Match findMatch(Player player1, Player player2) {
        for (Match match : matchSet) {
            Player[] twoPlayer = match.getTwoPlayers();
            if (twoPlayer[0] == player1 && twoPlayer[1] == player2) {
                return match;
            }
        }
        throw new ChampionshipException("no such match");
    }
}
