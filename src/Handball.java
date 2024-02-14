import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Handball extends Sports{
	
	static ArrayList<Handball> handball_team_list = new ArrayList<Handball>();
	static ArrayList<String> handball_team_name_list = new ArrayList<String>();
	

	public Handball(int point_for_win, int point_for_tie, int point_for_loss, String club_name) {
		super(point_for_win, point_for_tie, point_for_loss, club_name);
	}
	
	public void update(ArrayList<Object> home_data,ArrayList<Object> away_data) 
	{
		this.setNumber_of_played_matches();
		this.setNumber_of_matches_won(match_status(home_data,away_data)); 
		this.setNumber_of_matches_tie(match_status(home_data,away_data));
		this.setNumber_of_matches_loss(match_status(home_data,away_data));
		this.setNumber_of_goal_scored(home_data);
		this.setNumber_of_goal_conceded(away_data);
		this.setNumber_of_points_earned(match_status(home_data,away_data),this);
	}

	static void handball_scoreboard(ArrayList<Handball> team_list, BufferedWriter writer) throws IOException {
		Collections.sort(team_list,Comparator.comparing(Sports::getClub_name));
		Collections.sort(team_list,Comparator.comparing(Sports::get_avarage).reversed());
		Collections.sort(team_list,Comparator.comparing(Sports::getNumber_of_points_earned).reversed());
		
		int place = 1;
		for (Sports team : team_list) {
			writer.write(place +"."+"\t"+ team.getClub_name() +"\t"+ team.getNumber_of_played_matches() +"\t"+ team.getNumber_of_matches_won() +"\t"+ team.getNumber_of_matches_tie() +"\t"+ team.getNumber_of_matches_loss() +"\t"+ team.getNumber_of_goal_scored() +":"+ team.getNumber_of_goal_conceded() +"\t"+ team.getNumber_of_points_earned());
			writer.newLine();
			place += 1;
		}
		writer.close();
	}
	
	static void handball_main_func(ArrayList<Object> home_data,ArrayList<Object> away_data) {
		int handball_team_counter = 1;
		for (Handball handball_team_home : Handball.handball_team_list) 
		{
			if (handball_team_home.getClub_name().equals(home_data.get(0))) 
			{
				handball_team_home.update(home_data, away_data);
				break;
			} 
			if (!handball_team_home.getClub_name().equals(home_data.get(0)) && Handball.handball_team_name_list.contains(home_data.get(0)))
			{
				;
			}
			if (!Handball.handball_team_name_list.contains(home_data.get(0)) && !Handball.handball_team_name_list.contains(home_data.get(0)))
			{
				
				Handball.handball_team_list.add(handball_team_counter,new Handball(2,1,0,(String) home_data.get(0)));
				Handball.handball_team_name_list.add((String) home_data.get(0));
				Handball.handball_team_list.get(handball_team_counter).update(home_data, away_data);
				handball_team_counter += 1;
				break;
			}
		}
		
		for (Handball handball_team_away : Handball.handball_team_list) 
		{
			if (handball_team_away.getClub_name().equals(away_data.get(0))) 
			{
				handball_team_away.update(away_data, home_data);
				break;
			} 
			if (!handball_team_away.getClub_name().equals(away_data.get(0)) && Handball.handball_team_name_list.contains(away_data.get(0)))
			{
				;
			}
			if (!handball_team_away.getClub_name().equals(home_data.get(0)) && !Handball.handball_team_name_list.contains(away_data.get(0)))
			{
				
				Handball.handball_team_list.add(handball_team_counter,new Handball(2,1,0,(String) away_data.get(0)));
				Handball.handball_team_name_list.add((String) away_data.get(0));
				Handball.handball_team_list.get(handball_team_counter).update(away_data,home_data);
				handball_team_counter += 1;
				break;
			}
		}
	}
}
