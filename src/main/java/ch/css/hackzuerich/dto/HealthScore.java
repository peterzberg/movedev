package ch.css.hackzuerich.dto;

/**
 * Created by zberg.
 */
public class HealthScore {
    private String user;
    private boolean enoughSteps;

    public boolean isEnoughSteps() {
        return enoughSteps;
    }

    public void setEnoughSteps(boolean enoughSteps) {
        this.enoughSteps = enoughSteps;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public static HealthScore fromSteps(final String user, final boolean enoughSteps){
        final HealthScore score = new HealthScore();
        score.setEnoughSteps(enoughSteps);
        score.setUser(user);
        return score;

    }
}
