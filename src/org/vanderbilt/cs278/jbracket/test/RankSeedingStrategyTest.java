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
		assertEquals(pair_test.get(0),comp1);
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
		assertEquals(first_match.get(0).getSeed(), 1);
		assertEquals(first_match.get(1).getSeed(), 4);
		List<Competitor> second_match = matches.get(1).getCompetitors();
		assertEquals(second_match.size(), 2);
		assertEquals(second_match.get(0).getSeed(), 3);
		assertEquals(second_match.get(1).getSeed(), 2);
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
		assertEquals(first_match.get(0).getSeed(), 1);
		assertEquals(first_match.get(1).getSeed(), Integer.MAX_VALUE);
		List<Competitor> second_match = matches.get(1).getCompetitors();
		assertEquals(second_match.size(), 2);
		assertEquals(second_match.get(0).getSeed(), 5);
		assertEquals(second_match.get(1).getSeed(), 4);
		List<Competitor> third_match = matches.get(2).getCompetitors();
		assertEquals(third_match.size(), 2);
		assertEquals(third_match.get(0).getSeed(), 3);
		assertEquals(third_match.get(1).getSeed(), 6);
		List<Competitor> fourth_match = matches.get(3).getCompetitors();
		assertEquals(fourth_match.size(), 2);
		assertEquals(fourth_match.get(0).getSeed(), 7);
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
		assertEquals(first_match.get(0).getSeed(), 1);
		assertEquals(first_match.get(1).getSeed(), Integer.MAX_VALUE);
		List<Competitor> second_match = matches.get(1).getCompetitors();
		assertEquals(second_match.size(), 2);
		assertEquals(second_match.get(0).getSeed(), 5);
		assertEquals(second_match.get(1).getSeed(), 4);
		List<Competitor> third_match = matches.get(2).getCompetitors();
		assertEquals(third_match.size(), 2);
		assertEquals(third_match.get(0).getSeed(), 3);
		assertEquals(third_match.get(1).getSeed(), 6);
		List<Competitor> fourth_match = matches.get(3).getCompetitors();
		assertEquals(fourth_match.size(), 2);
		assertEquals(fourth_match.get(0).getSeed(), Integer.MAX_VALUE);
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
		boolean[] ranks = new boolean[64];
		Arrays.fill(ranks, false);
		for(Match match : matches)
		{
			List<Competitor> comps = match.getCompetitors();
			assertEquals(comps.size(), 2);
			assertEquals(comps.get(0).getSeed() + comps.get(1).getSeed(), 65);
			ranks[comps.get(0).getSeed() - 1] = true;
			ranks[comps.get(1).getSeed() - 1] = true;
		}
		boolean testAllRanks = true;
		for(boolean b : ranks)
		{
			testAllRanks = testAllRanks && b;
		}
		assertTrue(testAllRanks);
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
		
	}

}
