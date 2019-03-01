using NUnit.Framework;
using championship;

namespace Tests
{
    public class PlayerTest
    {
        [SetUp]
        public void Setup()
        {
        }

        [Test]
        public void EqualityTest()
        {
            Player p1 = new Player("bob");
            Player p2 = new Player("bob");
            Player p3 = new Player("alice");
            Assert.IsFalse(p1 == p2);
            Assert.IsTrue(p1 != p2);
            Assert.IsTrue(p1.Equals(p2));
            Assert.IsFalse(p1 == p3);
        }
    }
}