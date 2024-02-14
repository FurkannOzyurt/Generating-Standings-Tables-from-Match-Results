import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		File file = new File(args[0]);
		Scanner reader = new Scanner(file);
		BufferedWriter f_writer = new BufferedWriter(new FileWriter("Football.txt"));
		BufferedWriter h_writer = new BufferedWriter(new FileWriter("Handball.txt"));
		BufferedWriter b_writer = new BufferedWriter(new FileWriter("Basketball.txt"));
		BufferedWriter v_writer = new BufferedWriter(new FileWriter("Volleyball.txt"));
		
		Football fc = new Football(3, 1, 0, "nothing");
		Football.football_team_list.add(0,fc);
		Football.football_team_name_list.add(fc.getClub_name());
		
		Handball hc = new Handball(2, 1, 0, "nothing");
		Handball.handball_team_list.add(0,hc);
		Handball.handball_team_name_list.add(hc.getClub_name());
		
		Basketball bc = new Basketball(2, 0, 1, "nothing");
		Basketball.basketball_team_list.add(0,bc);
		Basketball.basketball_team_name_list.add(bc.getClub_name());
		
		Volleyball vc = new Volleyball(3, 0, 0, "nothing");
		Volleyball.volleyball_team_list.add(0,vc);
		Volleyball.volleyball_team_name_list.add(vc.getClub_name());
		
		while (reader.hasNext()) 
		{
			ArrayList<Object> line = new ArrayList<Object>(Arrays.asList(reader.nextLine().split("\t")));
			ArrayList<Object> score = new ArrayList<Object>(Arrays.asList(((String) line.get(3)).split(":")));
			ArrayList<Object> home_data = new ArrayList<Object>();
			ArrayList<Object> away_data = new ArrayList<Object>();
			home_data.add(line.get(1));
			home_data.add(score.get(0));
			away_data.add(line.get(2));
			away_data.add(score.get(1));
			
			if (line.get(0).equals("F")) 
			{
				Football.football_main_func(home_data, away_data);	
			}
			else if (line.get(0).equals("H")) 
			{
				Handball.handball_main_func(home_data, away_data);
			}
			else if (line.get(0).equals("B")) 
			{
				Basketball.basketball_main_func(home_data, away_data);
			}
			else 
			{
				Volleyball.basketball_main_func(home_data, away_data);
			}
		}
		
		Football.football_team_list.remove(0);
	    Football.football_scoreboard(Football.football_team_list,f_writer);
	    Handball.handball_team_list.remove(0);
	    Handball.handball_scoreboard(Handball.handball_team_list,h_writer);
	    Basketball.basketball_team_list.remove(0);
	    Basketball.basketball_scoreboard(Basketball.basketball_team_list,b_writer);
	    Volleyball.volleyball_team_list.remove(0);
	    Volleyball.volleyball_scoreboard(Volleyball.volleyball_team_list,v_writer);
	    
		reader.close();
		}		
}
