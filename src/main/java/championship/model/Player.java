package championship.model;

import java.util.Objects;

class Player {
    private String nickName;

    Player(String nickName) {
        if (nickName == null) {
            throw new IllegalArgumentException("nickName cannot be null");
        }
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Player)) {
            return false;
        } else {
            Player otherPlayer = (Player) other;
            boolean equals = nickName.compareTo(otherPlayer.nickName) == 0;
            return equals;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNickName());
    }

    @Override
    public String toString() {
        String res = "Player nickName=" + nickName;
        return  res;
    }
}
