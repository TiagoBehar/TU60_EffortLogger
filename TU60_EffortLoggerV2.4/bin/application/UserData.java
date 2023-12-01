/*
 Written by:
 -Tiago Behar
 -...
 */

package application;

public class UserData {
	private String project;
	private String step;
	private String category;
	private String deliverable;
	private String description;
	//private LocalDateTime startTime;
	//private LocalDateTime stopTime;
	private String formattedDate;
	private long minutes;
	private long seconds;
	
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDeliverable() {
		return deliverable;
	}
	public void setDeliverable(String deliverable) {
		this.deliverable = deliverable;
	}
	public long getMinutes() {
		return minutes;
	}
	public void setMinutes(long minutes) {
		this.minutes = minutes;
	}
	public long getSeconds() {
		return seconds;
	}
	public void setSeconds(long seconds) {
		this.seconds = seconds;
	}
	//Constructor:
	public UserData(String project, String step, String category, String deliverable, String description, String formattedDate, long minutes, long seconds) {
		this.project = project;
		this.step = step;
		this.category = category;
		this.deliverable = deliverable;
		this.description = description;
		this.formattedDate = formattedDate;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	public String getFormattedDate() {
		return formattedDate;
	}
	public void setFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
