package mvc;

public class JobTest {
	public static void main(String[] args) {
		Job model = getJob();

		JobView view = new JobView();

		JobController controller = new JobController(model, view);

		controller.updateView();

		controller.setJobTitle("Java Developer Needed!");

		controller.updateView();
	}

	private static Job getJob() {
		Job job = new Job();
		job.setJobTitle("Java Developer Needed!");
		job.setJobPay("$20,000");
		return job;
	}
}

class Job {
	private String jobtitle;
	private String jobpay;

	// Set Job title
	public void setJobTitle(String title) {
		this.jobtitle = title;
	}

	// Set Job Pay
	public void setJobPay(String pay) {
		this.jobpay = pay;
	}

	// Getter for job title
	public String getJobTitle() {
		return this.jobtitle;
	}

	// Getter for job pay
	public String getJobPay() {
		return this.jobpay;
	}
}

class JobController {
	private Job model;
	private JobView view;

	public JobController(Job model, JobView view) {
		this.model = model;
		this.view = view;
	}

	public void setJobTitle(String title) {
		model.setJobTitle(title);
	}

	public void setJobPay(String pay) {
		model.setJobPay(pay);
	}

	public String getJobTitle() {
		return model.getJobTitle();
	}

	public String getJobPay() {
		return model.getJobPay();
	}

	public void updateView() {
		view.displayjobs(model.getJobTitle(), model.getJobPay());
	}
}

// Jobs View
class JobView {
	public void displayjobs(String jobtitle, String jobpay) {
		System.out.println("Job title is: " + jobtitle);
		System.out.println("Starting pay is: " + jobpay);
	}
}