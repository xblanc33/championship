namespace championship 
{
    public class ChampionshipException : System.Exception
    {
        public ChampionshipException() { }
        public ChampionshipException(string message) : base(message) { }
        public ChampionshipException(string message, System.Exception inner) : base(message, inner) { }
        protected ChampionshipException(
            System.Runtime.Serialization.SerializationInfo info,
            System.Runtime.Serialization.StreamingContext context) : base(info, context) { }
    }
}