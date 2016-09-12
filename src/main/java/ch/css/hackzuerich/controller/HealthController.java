package ch.css.hackzuerich.controller;

import ch.css.hackzuerich.dto.HealthScore;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zberg.
 */
@RestController
public class HealthController {

    private static final Map<String, Boolean> SCORES = new HashMap<>();

    static {
        SCORES.put("pesche", Boolean.TRUE);
        SCORES.put("jarek", Boolean.TRUE);
    }

    @RequestMapping("/hackzuerich/health/{user}")
    public HealthScore healthScore(@PathVariable("user") final String user) {
        validateUser(user);
        return HealthScore.fromSteps(user, SCORES.get(user));
    }

    @RequestMapping("/hackzuerich/health")
    public List<HealthScore> overallScores() {
        return SCORES.entrySet().stream().map(entry -> HealthScore.fromSteps(entry.getKey(), entry.getValue())).collect(Collectors.toList());
    }

    /**
     * Toggles the fact if the given user has enough steps. I guess this would not be in production
     * @param user the user to toggle :)
     * @return new health score
     */
    @RequestMapping(value = "/hackzuerich/health/{user}", method = RequestMethod.PUT)
    public HealthScore toggleScore(@PathVariable("user") final String user) {
        validateUser(user);
        SCORES.put(user, !SCORES.get(user));
        return healthScore(user);
    }

    private void validateUser(final String user) {
        if (!SCORES.containsKey(user)) {
            throw new UserNotFoundException();
        }
    }
}
