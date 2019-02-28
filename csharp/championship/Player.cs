using System;

namespace championship
{
    public class Player
    {
        private string _nickname;

        public Player(string nickname)
        {
            if (nickname == null)
                throw new ArgumentNullException ("url");
            _nickname = nickname;
        }

        public string NickName 
        {
            get {
                return _nickname;
            }
        }

        public override bool Equals(Object obj) 
        {
            return obj is Player && this == (Player)obj;
        }

        public override int GetHashCode() 
        {
            return _nickname.GetHashCode();
        }

        public static bool operator ==(Player x, Player y) 
        {
            return x._nickname == y._nickname;
        }

        public static bool operator !=(Player x, Player y) 
        {
            return !(x == y);
        }
    }
}
