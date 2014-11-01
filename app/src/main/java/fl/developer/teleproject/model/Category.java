package fl.developer.teleproject.model;

import java.io.Serializable;

/**
 * Created by alexk on 01.11.2014.
 */
public class Category implements Serializable{

    private final int iconRes;
    private final String name;
    private final int score;
    private int siteAverage;

    public Category(int iconRes, String name, int score, int siteAverage) {
        this.iconRes = iconRes;
        this.name = name;
        this.score = score;
        this.siteAverage = siteAverage;
    }

    public int getIconRes() {
        return iconRes;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getSiteAverageString() {
        return "site average: " + siteAverage;
    }

    public int getSiteAverage() {
        return siteAverage;
    }
}
