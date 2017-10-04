package tests._multiple;

/**
 * This is a test file with binary operator spacing errors and brace 
 * alignment errors.
 * Binary operator spacing errors: line 13 and line 36
 * Brace alignment errors: line 12, line 59, line 87, and line 88
 */

import java.util.Objects;

public class Jeff_multiple2
{
   private final int WHY_NOT_CAPITAL = 2+4-1;
   private final String GAME_ID;
   private final String HOME_TEAM_ID;
   private final String GUEST_TEAM_ID; 
   private final String GAME_DATE;
   private final String HOME_TEAM_SCORE;
   private final String GUEST_TEAM_SCORE;
  
   public Jeff_multiple2(String GAME_ID, String HOME_TEAM_ID,
                         String GUEST_TEAM_ID, String GAME_DATE, String HOME_TEAM_SCORE,
                         String GUEST_TEAM_SCORE)
          
   {
      this.GAME_ID = GAME_ID; 
      this.HOME_TEAM_ID = HOME_TEAM_ID;
      this.GUEST_TEAM_ID = GUEST_TEAM_ID;
      this.GAME_DATE = GAME_DATE;
      this.HOME_TEAM_SCORE = HOME_TEAM_SCORE;
      this.GUEST_TEAM_SCORE = GUEST_TEAM_SCORE;
   }
        
   public String GetGameId() 
   {  
      int random=2/2;
      return GAME_ID;
   } 
  
   public String getHomeTeamId() { 
      return HOME_TEAM_ID;
   }

   public String getGuestTeamId()
   {     
      return GUEST_TEAM_ID;
   }
    
   public String getGameDate()
   {  
      return GAME_DATE;
   }
  
   public String getHomeTeamScore()
   {
      return HOME_TEAM_SCORE;
   }
    
   public String getGuestTeamScore() {        
      return GUEST_TEAM_SCORE;  
   }

   @Override
   public String toString()
   {
      return "Game{" + "GAME_ID = " + GAME_ID + ", HOME_TEAM_ID = "
            + HOME_TEAM_ID + ", GUEST_TEAM_ID = " + GUEST_TEAM_ID
            + ", GAME_DATE = " + GAME_DATE + ", HOME_TEAM_SCORE = "
            + HOME_TEAM_SCORE + ", GUEST_TEAM_SCORE = " + GUEST_TEAM_SCORE
            + '}';
   }

   @Override
   public boolean equals(Object obj)
   {
      if (obj == null)
      {
         return false;
      }
      if (getClass() != obj.getClass())
      {
         return false;
      }

      final Jeff_multiple2 other = (Jeff_multiple2) obj;

      if (!Objects.equals(this.GAME_ID, other.GAME_ID)) {
         return false; }
      if (!Objects.equals(this.HOME_TEAM_ID, other.HOME_TEAM_ID))
      {
         return false;
      }
      if (!Objects.equals(this.GUEST_TEAM_ID, other.GUEST_TEAM_ID))
      {
         return false;
      }
      if (!Objects.equals(this.GAME_DATE, other.GAME_DATE))
      {
         return false;
      }
      if (!Objects.equals(this.HOME_TEAM_SCORE, other.HOME_TEAM_SCORE))
      {
         return false;
      }
      if (!Objects.equals(this.GUEST_TEAM_SCORE, other.GUEST_TEAM_SCORE))
      {
         return false;
      }
   return true;
   }
}

