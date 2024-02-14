import java.util.ArrayList;

public class Sports {
	private int point_for_win;
	private int point_for_tie;
	private int point_for_loss;
	private int ranking;
	private String club_name;
	private int number_of_played_matches;
	private int number_of_matches_won;
	private int number_of_matches_tie;
	private int number_of_matches_loss;
	private int number_of_goal_scored;
	private int number_of_goal_conceded;
	public int number_of_points_earned;
	
	public Sports(int point_for_win, int point_for_tie, int point_for_loss, String club_name) {
		this.point_for_win = point_for_win;
		this.point_for_tie = point_for_tie;
		this.point_for_loss = point_for_loss;
		this.club_name = club_name;
	}

	public int getPoint_for_win() {
		return point_for_win;
	}

	public int getPoint_for_tie() {
		return point_for_tie;
	}

	public int getPoint_for_loss() {
		return point_for_loss;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public String getClub_name() {
		return club_name;
	}

	public int getNumber_of_played_matches() {
		return number_of_played_matches;
	}

	public void setNumber_of_played_matches() {
		this.number_of_played_matches += 1;
	}

	public int getNumber_of_matches_won() {
		return number_of_matches_won;
	}

	public void setNumber_of_matches_won(String match_status) {
		if (match_status.equals("win")) 
		{
			this.number_of_matches_won += 1;
		} 
	}

	public int getNumber_of_matches_tie() {
		return number_of_matches_tie;
	}

	public void setNumber_of_matches_tie(String match_status) {
		if (match_status.equals("tie")) 
		{
			this.number_of_matches_tie += 1;
		} 
	}

	public int getNumber_of_matches_loss() {
		return number_of_matches_loss;
	}

	public void setNumber_of_matches_loss(String match_status) {
		if (match_status.equals("loss")) 
		{
			this.number_of_matches_loss += 1;
		} 
	}

	public int getNumber_of_goal_scored() {
		return number_of_goal_scored;
	}

	public void setNumber_of_goal_scored(ArrayList<Object> home_data) {
		this.number_of_goal_scored += Integer.parseInt((String) home_data.get(1));
	}
	
	public int getNumber_of_goal_conceded() {
		return number_of_goal_conceded;
	}

	public void setNumber_of_goal_conceded(ArrayList<Object> away_data) {
		this.number_of_goal_conceded += Integer.parseInt((String) away_data.get(1));
	}

	public int getNumber_of_points_earned() {
		return number_of_points_earned;
	}

	public void setNumber_of_points_earned(String match_status, Sports sport) {
		if (match_status.equals("win")) 
		{
			this.number_of_points_earned += sport.getPoint_for_win();
		} 
		else if (match_status.equals("tie")) 
		{
			this.number_of_points_earned += sport.getPoint_for_tie();
		}
		else 
		{
			this.number_of_points_earned += sport.getPoint_for_loss();
		}
	}
	
	public String match_status(ArrayList<Object> home_data, ArrayList<Object> away_data) {
		if (Integer.parseInt((String) home_data.get(1)) > (Integer.parseInt((String) away_data.get(1))))
		{
			return "win";
		} 
		else if (Integer.parseInt((String) home_data.get(1)) < (Integer.parseInt((String) away_data.get(1)))) 
		{
			return "loss";
		}
		else 
		{
			return "tie";
		}
	}
	
	public int get_avarage() {
		return this.getNumber_of_goal_scored() - this.getNumber_of_goal_conceded();
	}
}





























