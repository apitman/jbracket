package org.vanderbilt.cs278.jbracket.model;

import java.util.*;

public interface SeedingStrategy {
	
	public void seed (List<Competitor> competitorList, Round round);
	
}
