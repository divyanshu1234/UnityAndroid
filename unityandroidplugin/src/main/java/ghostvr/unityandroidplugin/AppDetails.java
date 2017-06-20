package ghostvr.unityandroidplugin;

/**
 * Created by Divyanshu on 6/20/17.
 */

public class AppDetails {
    private String label, name;

    public AppDetails(String label, String name) {
        this.label = label;
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name;
    }
}
