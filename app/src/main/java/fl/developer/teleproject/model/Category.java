package fl.developer.teleproject.model;

import java.io.Serializable;

/**
 * Created by alexk on 01.11.2014.
 */
public class Category implements Serializable{

    private final int iconRes;
    private final String name;
    private final double score;
    private double siteAverage;
    private String averageSpeed;

    public Category(int iconRes, String name, double score, double siteAverage) {
        this.iconRes = iconRes;
        this.name = name;
        this.score = score;
        this.siteAverage = siteAverage;
    }

    public Category(int iconRes, String name, double score, double siteAverage, String averageSpeed) {
        this(iconRes, name, score, siteAverage);
        this.averageSpeed = averageSpeed;
    }

    public int getIconRes() {
        return iconRes;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public String getSiteAverageString() {
        return "site average: " + siteAverage;
    }

    public double getSiteAverage() {
        return siteAverage;
    }

    public String getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(String averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

}
