package application;
//defect class for defectConsoleViewController
public class defects {
	private String defect;
	private String project;
	private String phase;
	private String fixed;
	private int effort;
			   
	public defects(String defect, String project, String phase, String fixed, int effort) {
		this.defect = defect;
		this.project = project;
		this.phase = phase;
		this.fixed = fixed;
		this.effort = effort;
	}
	public String getDefect() {
        return defect;
	}
	public void setDefect(String defect) {
		this.defect = defect;
	}
	public String getProject() {
        return project;
	}
	public void setProject(String project) {
        this.project = project;
	}
	public String getPhase() {
        return phase;
 	}
	public void setPhase(String phase) {
        this.phase = phase;
 	}
 	public String getFixed() {
        return fixed;
 	}
 	public void setFixed(String fixed) {
        this.fixed = fixed;
 	}
 	public int getEffort() {
        return effort;
 	}
 	public void setEffort(int effort) {
        this.effort = effort;
 	} 
}
