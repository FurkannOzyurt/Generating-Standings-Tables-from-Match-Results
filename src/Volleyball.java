import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Volleyball extends Sports{
	
	static ArrayList<Volleyball> volleyball_team_list = new ArrayList<Volleyball>();
	static ArrayList<String> volleyball_team_name_list = new ArrayList<String>();
	

	public Volleyball(int point_for_win, int point_for_tie, int point_for_loss, String club_name) {
		super(point_for_win, point_for_tie, point_for_loss, club_name);
	}
	
	public void update(ArrayList<Object> home_data,ArrayList<Object> away_data) 
	{
		setNumber_of_played_matches();
		setNumber_of_matches_won(match_status(home_data,away_data)); 
		setNumber_of_matches_tie(match_status(home_data,away_data));
		setNumber_of_matches_loss(match_status(home_data,away_data));
		setNumber_of_goal_scored(home_data);
		setNumber_of_goal_conceded(away_data);
		setNumber_of_points_earned_volleyball(match_status(home_data,away_data),this,home_data,away_data);
	}

	static void volleyball_scoreboard(ArrayList<Volleyball> team_list, BufferedWriter writer) throws IOException {
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
	
	public void setNumber_of_points_earned_volleyball(String match_status, Sports sport, ArrayList<Object> home_data, ArrayList<Object> away_data) {
		if (match_status.equals("win")) 
		{
			if (((home_data.get(1).equals("3")) && ((away_data.get(1).equals("1")) || (away_data.get(1).equals("0"))))) {
				this.number_of_points_earned += sport.getPoint_for_win();
			}
			else {
				this.number_of_points_earned += (sport.getPoint_for_win() - 1);
			}
			
		}
		else if (match_status.equals("tie")) {
			this.number_of_points_earned += (sport.getPoint_for_tie());
		}
		else 
		{
			if (((away_data.get(1).equals("3")) && ((home_data.get(1).equals("1")) || (home_data.get(1).equals("0"))))) {
				this.number_of_points_earned += sport.getPoint_for_loss();
			}
			else {
				this.number_of_points_earned += (sport.getPoint_for_loss() + 1);
			}
		}
	}
	
	static void basketball_main_func(ArrayList<Object> home_data,ArrayList<Object> away_data) {
		int volleyball_team_counter = 1;
		for (Volleyball volleyball_team_home : Volleyball.volleyball_team_list) 
		{
			if (volleyball_team_home.getClub_name().equals(home_data.get(0))) 
			{
				volleyball_team_home.update(home_data, away_data);
				break;
			} 
			if (!volleyball_team_home.getClub_name().equals(home_data.get(0)) && Volleyball.volleyball_team_name_list.contains(home_data.get(0)))
			{
				;
			}
			if (!Volleyball.volleyball_team_name_list.contains(home_data.get(0)) && !Volleyball.volleyball_team_name_list.contains(home_data.get(0)))
			{
				
				Volleyball.volleyball_team_list.add(volleyball_team_counter,new Volleyball(3,0,0,(String) home_data.get(0)));
				Volleyball.volleyball_team_name_list.add((String) home_data.get(0));
				Volleyball.volleyball_team_list.get(volleyball_team_counter).update(home_data, away_data);
				volleyball_team_counter += 1;
				break;
			}
		}
		
		for (Volleyball volleyball_team_away : Volleyball.volleyball_team_list) 
		{
			if (volleyball_team_away.getClub_name().equals(away_data.get(0))) 
			{
				volleyball_team_away.update(away_data, home_data);
				break;
			} 
			if (!volleyball_team_away.getClub_name().equals(away_data.get(0)) && Volleyball.volleyball_team_name_list.contains(away_data.get(0)))
			{
				;
			}
			if (!Volleyball.volleyball_team_name_list.contains(away_data.get(0)) && !Volleyball.volleyball_team_name_list.contains(away_data.get(0)))
			{
				
				Volleyball.volleyball_team_list.add(volleyball_team_counter,new Volleyball(3,0,0,(String) away_data.get(0)));
				Volleyball.volleyball_team_name_list.add((String) away_data.get(0));
				Volleyball.volleyball_team_list.get(volleyball_team_counter).update(away_data, home_data);
				volleyball_team_counter += 1;
				break;
			}
		}			
	}
	
	
}
