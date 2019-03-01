using NUnit.Framework;
using championship;

namespace Tests
{
    public class MatchTest
    {
        [SetUp]
        public void Setup()
        {
        }

        [Test]
        public void StartTest()
        {
            Player winner = new Player("winner");
            Player loser = new Player("loser");
            Match m = new Match(winner, loser);
            m.Start();
            Assert.IsTrue(m.Started);
            m.UpdateScorePlayer1(2);
            m.UpdateScorePlayer2(0);
        }
    }
}