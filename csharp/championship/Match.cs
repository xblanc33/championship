using System;

namespace championship
{
    public class Match
    {
        private Player _player1, _player2;

        private bool _started, _closed;

        private int _score1, _score2;

        public Match(Player player1, Player player2) {
            if (player1 == null) 
            {
                throw new ArgumentNullException("player1 is null");
            }

            if (player2 == null) 
            {
                throw new ArgumentNullException("player2 is null");
            }

            _player1 = player1;
            _player2 = player2;

            _started = false;
            _closed = false;
        }

        public bool Started
        {
            get
            {
                return _started;
            }
        }

        public bool Closed
        {
            get 
            {
                return _closed;
            }
        }

        public Player[] TwoPlayers
        {
            get
            {
                return new Player[2] {_player1, _player2};
            }
        }

        public void Start()
        {
            if (_started) 
            {
                throw new ChampionshipException("Already started !");
            }
            _started = true;
        }

        public void Close()
        {
            if (! _started) 
            {
                throw new ChampionshipException("Can't close unstarted match !");
            }
            if (_closed) 
            {
                throw new ChampionshipException("Match already closed !");
            }
            _closed = true;
        }

        public bool IsDraw() 
        {
            if (! _closed) 
            {
                throw new ChampionshipException("Match Still Open");
            }
            return _score1 == _score2;
        }

        public void UpdateScorePlayer1(int score) 
        {
            setScore(1, score);
        }

        public void UpdateScorePlayer2(int score) 
        {
            setScore(2, score);
        }

        public Player Winner 
        {
            get
            {
                if (!_closed) 
                {
                    throw new ChampionshipException("Match still open");
                }
                if (IsDraw()) 
                {
                    throw new ChampionshipException("No winner");
                }
                if (_score1 >= _score2) 
                {
                    return _player1;
                } 
                else 
                {
                    return _player2;
                }
            }
        }

        private void setScore(int number, int score) 
        {
            if (!_started) 
            {
                throw new ChampionshipException("match not started");
            }
            if (_closed) 
            {
                throw new ChampionshipException("match is closed");
            }
            if (score < 0) 
            {
                throw new ChampionshipException("Score must be greater than 0");
            }
            switch (number) {
                case 1 : _score1 = score;
                        break;
                case 2 : _score2 = score;
                        break;
            }
        }

    }
}
