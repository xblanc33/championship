using System;

namespace championship
{
    public class Player
    {
        private readonly string _nickname;

        public Player(string nickname)
        {
            if (nickname == null)
            {
                throw new ArgumentNullException ("nickname");
            }
            _nickname = nickname;
        }

        public string NickName 
        {
            get 
            {
                return _nickname;
            }
        }

        public override bool Equals(Object obj) 
        {
            return obj is Player && this._nickname == ((Player)obj)._nickname;
        }

        public override int GetHashCode() 
        {
            return _nickname.GetHashCode();
        }
    }
}
