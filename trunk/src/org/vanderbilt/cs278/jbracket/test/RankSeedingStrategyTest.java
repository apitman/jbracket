package org.vanderbilt.cs278.jbracket.test;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.vanderbilt.cs278.jbracket.model.Competitor;
import org.vanderbilt.cs278.jbracket.model.Match;
import org.vanderbilt.cs278.jbracket.model.RankSeedingStrategy;
import org.vanderbilt.cs278.jbracket.model.Round;
import org.vanderbilt.cs278.jbracket.test.dummies.CompetitorDummy;
import org.vanderbilt.cs278.jbracket.test.dummies.RoundDummy;

import junit.framework.TestCase;

public class RankSeedingStrategyTest extends TestCase {
	/*
	 * These tests test the smallest case (two competitors), 
	 * basic cases of byes, a large case (64 competitors), plus
	 * a case with lots of byes (15 out of 32 matches).  We
	 *  ensured that it placed competitors in the matches.  We 
	 * ensured that it put the competitors in the correct matches
	 * and order based on their seed.  We ensured that it placed
	 * byes in the right matches.
	 * 
	 */
	private class Pair
	{
		public int c1;
		public int c2;
		
		public Pair(int c1, int c2)
		{
			this.c1 = c1;
			this.c2 = c2;
		}
		
	}
	
	public void testSeedBasic()
	{
		List<Competitor> pair = new ArrayList<Competitor>();
		Competitor comp1 = new CompetitorDummy();
		Competitor comp2 = new CompetitorDummy();
		comp1.setSeed(1);
		comp2.setSeed(2);
		pair.add(comp1);
		pair.add(comp2);
		RankSeedingStrategy rss = new RankSeedingStrategy();
		Round r = new RoundDummy();
		rss.seed(pair, r);
		List<Match> matches = r.getMatches();
		assertEquals(matches.size(),1);
		List<Competitor> pair_test = matches.get(0).getCompetitors();
		
		assertEquals(pair_test.size(),2);
		assertNotNull(pair_test.get(0));
		assertEquals(pair_test.get(0),comp1);
		assertNotNull(pair_test.get(1));
		assertEquals(pair_test.get(1),comp2);
	}
	
	public void testSeedFour()
	{
		List<Competitor> competitorList = new ArrayList<Competitor>();
		for(int i = 0; i < 4; i++)
		{
			Competitor comp = new CompetitorDummy();
			comp.setSeed(i + 1);
			competitorList.add(comp);
		}
		RankSeedingStrategy rss = new RankSeedingStrategy();
		Round r = new RoundDummy();
		rss.seed(competitorList, r);
		List<Match> matches = r.getMatches();
		assertEquals(matches.size(), 2);
		List<Competitor> first_match = matches.get(0).getCompetitors();
		assertEquals(first_match.size(), 2);
		assertNotNull(first_match.get(0));
		assertEquals(first_match.get(0).getSeed(), 1);
		assertNotNull(first_match.get(1));
		assertEquals(first_match.get(1).getSeed(), 4);
		List<Competitor> second_match = matches.get(1).getCompetitors();
		assertEquals(second_match.size(), 2);
		assertEquals(second_match.get(0).getSeed(), 3);
		assertNotNull(second_match.get(0));
		assertEquals(second_match.get(1).getSeed(), 2);
		assertNotNull(second_match.get(1));
	}
	
	public void testSeedByeSeven()
	{
		List<Competitor> competitorList = new ArrayList<Competitor>();
		for(int i = 0; i < 7; i++)
		{
			Competitor comp = new CompetitorDummy();
			comp.setSeed(i + 1);
			competitorList.add(comp);
		}
		RankSeedingStrategy rss = new RankSeedingStrategy();
		Round r = new RoundDummy();
		rss.seed(competitorList, r);
		List<Match> matches = r.getMatches();
		assertEquals(matches.size(), 4);
		List<Competitor> first_match = matches.get(0).getCompetitors();
		assertEquals(first_match.size(), 2);
		assertNotNull(first_match.get(0));
		assertEquals(first_match.get(0).getSeed(), 1);
		assertNotNull(first_match.get(1));
		assertEquals(first_match.get(1).getSeed(), Integer.MAX_VALUE);
		List<Competitor> second_match = matches.get(1).getCompetitors();
		assertEquals(second_match.size(), 2);
		assertNotNull(second_match.get(0));
		assertEquals(second_match.get(0).getSeed(), 5);
		assertNotNull(second_match.get(1));
		assertEquals(second_match.get(1).getSeed(), 4);
		List<Competitor> third_match = matches.get(2).getCompetitors();
		assertEquals(third_match.size(), 2);
		assertNotNull(third_match.get(0));
		assertEquals(third_match.get(0).getSeed(), 3);
		assertNotNull(third_match.get(1));
		assertEquals(third_match.get(1).getSeed(), 6);
		List<Competitor> fourth_match = matches.get(3).getCompetitors();
		assertEquals(fourth_match.size(), 2);
		assertNotNull(fourth_match.get(0));
		assertEquals(fourth_match.get(0).getSeed(), 7);
		assertNotNull(fourth_match.get(1));
		assertEquals(fourth_match.get(1).getSeed(), 2);
	}
	
	public void testSeedByeSix()
	{
		List<Competitor> competitorList = new ArrayList<Competitor>();
		for(int i = 0; i < 6; i++)
		{
			Competitor comp = new CompetitorDummy();
			comp.setSeed(i + 1);
			competitorList.add(comp);
		}
		RankSeedingStrategy rss = new RankSeedingStrategy();
		Round r = new RoundDummy();
		rss.seed(competitorList, r);
		List<Match> matches = r.getMatches();
		assertEquals(matches.size(), 4);
		List<Competitor> first_match = matches.get(0).getCompetitors();
		assertEquals(first_match.size(), 2);
		assertNotNull(first_match.get(0));
		assertEquals(first_match.get(0).getSeed(), 1);
		assertNotNull(first_match.get(1));
		assertEquals(first_match.get(1).getSeed(), Integer.MAX_VALUE);
		List<Competitor> second_match = matches.get(1).getCompetitors();
		assertEquals(second_match.size(), 2);
		assertNotNull(second_match.get(0));
		assertEquals(second_match.get(0).getSeed(), 5);
		assertNotNull(second_match.get(1));
		assertEquals(second_match.get(1).getSeed(), 4);
		List<Competitor> third_match = matches.get(2).getCompetitors();
		assertEquals(third_match.size(), 2);
		assertNotNull(third_match.get(0));
		assertEquals(third_match.get(0).getSeed(), 3);
		assertNotNull(third_match.get(1));
		assertEquals(third_match.get(1).getSeed(), 6);
		List<Competitor> fourth_match = matches.get(3).getCompetitors();
		assertEquals(fourth_match.size(), 2);
		assertNotNull(fourth_match.get(0));
		assertEquals(fourth_match.get(0).getSeed(), Integer.MAX_VALUE);
		assertNotNull(fourth_match.get(1));
		assertEquals(fourth_match.get(1).getSeed(), 2);
	}
	
	public void testSeedBIG()
	{
		List<Competitor> competitorList = new ArrayList<Competitor>();
		for(int i = 0; i < 64; i++)
		{
			Competitor comp = new CompetitorDummy();
			comp.setSeed(i + 1);
			competitorList.add(comp);
		}
		RankSeedingStrategy rss = new RankSeedingStrategy();
		Round r = new RoundDummy();
		rss.seed(competitorList, r);
		List<Match> matches = r.getMatches();
		assertEquals(matches.size(), 32);
		Pair[] ranks = createRankOrdering(64);
		for(int i = 0; i < 32; i++)
		{
			Match match = matches.get(i);
			List<Competitor> comps = match.getCompetitors();
			assertEquals(comps.size(), 2);
			assertEquals(comps.get(0).getSeed(), ranks[i].c1);
			assertNotNull(comps.get(0));
			assertEquals(comps.get(1).getSeed(), ranks[i].c2);
			assertNotNull(comps.get(1));
		}
	}
	
	public void testSeedThirtyThree()
	{
		List<Competitor> competitorList = new ArrayList<Competitor>();
		for(int i = 0; i < 33; i++)
		{
			Competitor comp = new CompetitorDummy();
			comp.setSeed(i + 1);
			competitorList.add(comp);
		}
		RankSeedingStrategy rss = new RankSeedingStrategy();
		Round r = new RoundDummy();
		rss.seed(competitorList, r);
		List<Match> matches = r.getMatches();
		assertEquals(matches.size(), 32);
		Pair[] ranks = createRankOrdering(64);
		for(int i = 0; i < 32; i++)
		{
			Match match = matches.get(i);
			List<Competitor> comps = match.getCompetitors();
			assertEquals(comps.size(), 2);
			assertNotNull(comps.get(0));
			assertNotNull(comps.get(1));
			if(ranks[i].c1 <= 33)
			{
				
				assertEquals(comps.get(0).getSeed(), ranks[i].c1);
			}
			else
			{
				assertEquals(comps.get(0).getSeed(), Integer.MAX_VALUE);
			}
			if(ranks[i].c2 <= 33)
			{
				assertEquals(comps.get(1).getSeed(), ranks[i].c2);
			}
			else
			{
				assertEquals(comps.get(1).getSeed(), Integer.MAX_VALUE);
			}
		}
		
	}
	
	public void testCreateRankOrdering()
	{
		Pair[] ranks = createRankOrdering(8);
		assertEquals(ranks[0].c1, 1);
		assertEquals(ranks[0].c2, 8);
		assertEquals(ranks[1].c1, 5);
		assertEquals(ranks[1].c2, 4);
		assertEquals(ranks[2].c1, 3);
		assertEquals(ranks[2].c2, 6);
		assertEquals(ranks[3].c1, 7);
		assertEquals(ranks[3].c2, 2);
	}
	
	public Pair[] createRankOrdering(int numComps)
	{
		if(numComps == 2)
		{
			Pair[] pair = new Pair[1];
			pair[0] = new Pair(1,2);
			return pair;
		}
		Pair[] temp = createRankOrdering(numComps/2);
		Pair[] retList = new Pair[numComps / 2];
		for(int i = 0; i < temp.length; i++)
		{
			retList[i * 2] = new Pair(temp[i].c1, numComps - temp[i].c1 + 1);
			retList[i * 2 + 1] = new Pair(numComps - temp[i].c2 + 1, temp[i].c2);
		}
		return retList;
	}

}
