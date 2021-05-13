
 /*
+ * Dashboard.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Double						averageNumberOfJobsPerEmployer;
	Double						averageNumberOfApplicationsPerWorker;
	Double						avegageNumberOfApplicationsPerEmployer;
	Double						ratioOfPendingApplications;
	Double						ratioOfAcceptedApplications;
	Double						ratioOfRejectedApplications;
	
	Integer						numberOfTasksPublic;
	Integer						numberOfTasksPrivate;
	Integer						numberOfTasksFinished;
	Integer						numberOfTasksUnfinished;
	
	Double						averageWorkload;
	Double						deviationWorkload;
	Double						maximumWorkload;
	Double						minimumWorkload;
	
	Double						averageExecutionPeriod;
	Double						deviationExecutionPeriod;
	Double						maximumExecutionPeriod;
	Double						minimumExecutionPeriod;
	
	Integer 					numberOfWorkPlans;
	Integer						numberOfWorkPlansPublic;
	Integer						numberOfWorkPlansPrivate;
	Integer						numberOfWorkPlansFinished;
	Integer						numberOfWorkPlansUnfinished;
	
	Double						averageWorkPlansWorkload;
	Double						deviationWorkPlansWorkload;
	Double						maximumWorkPlansWorkload;
	Double						minimumWorkPlansWorkload;
	
	Double						averageWorkPlansExecutionPeriod;
	Double						deviationWorkPlansExecutionPeriod;
	Double						maximumWorkPlansExecutionPeriod;
	Double						minimumWorkPlansExecutionPeriod;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}