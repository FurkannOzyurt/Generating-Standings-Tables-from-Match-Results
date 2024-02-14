import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Football extends Sports{
	
	static ArrayList<Football> football_team_list = new ArrayList<Football>();
	static ArrayList<String> football_team_name_list = new ArrayList<String>();
	

	public Football(int point_for_win, int point_for_tie, int point_for_loss, String club_name) {
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

	static void football_scoreboard(ArrayList<Football> team_list, BufferedWriter writer) throws IOException {
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
	
	static void football_main_func(ArrayList<Object> home_data,ArrayList<Object> away_data) {
		int football_team_counter = 1;
		for (Football football_team_home : Football.football_team_list) 
		{
			if (football_team_home.getClub_name().equals(home_data.get(0))) 
			{
				football_team_home.update(home_data, away_data);
				break;
			} 
			if (!football_team_home.getClub_name().equals(home_data.get(0)) && Football.football_team_name_list.contains(home_data.get(0)))
			{
				;
			}
			if (!Football.football_team_name_list.contains(home_data.get(0)) && !Football.football_team_name_list.contains(home_data.get(0)))
			{
				
				Football.football_team_list.add(football_team_counter,new Football(3,1,0,(String) home_data.get(0)));
				Football.football_team_name_list.add((String) home_data.get(0));
				Football.football_team_list.get(football_team_counter).update(home_data, away_data);
				football_team_counter += 1;
				break;
			}
		}
		
		for (Football football_team_away : Football.football_team_list) 
		{
			if (football_team_away.getClub_name().equals(away_data.get(0))) 
			{
				football_team_away.update(away_data, home_data);
				break;
			} 
			if (!football_team_away.getClub_name().equals(away_data.get(0)) && Football.football_team_name_list.contains(away_data.get(0)))
			{
				;
			}
			if (!football_team_away.getClub_name().equals(home_data.get(0)) && !Football.football_team_name_list.contains(away_data.get(0)))
			{
				
				Football.football_team_list.add(football_team_counter,new Football(3,1,0,(String) away_data.get(0)));
				Football.football_team_name_list.add((String) away_data.get(0));
				Football.football_team_list.get(football_team_counter).update(away_data,home_data);
				football_team_counter += 1;
				break;
			}
		}
	}
}
