<%--
- form.jsp
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message
		code="administrator.dashboard.form.title.general-indicators" />
</h2>

<table class="table table-sm">
	<caption>
		<acme:message
			code="administrator.dashboard.form.title.general-indicators" />
	</caption>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.average-number-jobs-employer" />
		</th>
		<td><acme:print value="${averageNumberOfJobsPerEmployer}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.average-number-applications-worker" />
		</th>
		<td><acme:print value="${averageNumberOfApplicationsPerWorker}" />
		</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.average-number-applications-employer" />
		</th>
		<td><acme:print value="${averageNumberOfApplicationsPerEmployer}" />
		</td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.total-number-task-public" />
		</th>
		<td><acme:print value="${numberOfTasksPublic}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.total-number-task-private" />
		</th>
		<td><acme:print value="${numberOfTasksPrivate}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.total-number-task-finished" />
		</th>
		<td><acme:print value="${numberOfTasksFinished}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.total-number-task-unfinished" />
		</th>
		<td><acme:print value="${numberOfTasksUnfinished}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.average-workload" /></th>
		<td><acme:print value="${averageWorkload}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.deviation-workload" /></th>
		<td><acme:print value="${deviationWorkload}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.maximum-workload" /></th>
		<td><acme:print value="${maximumWorkload}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.minimum-workload" /></th>
		<td><acme:print value="${minimumWorkload}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.average-execution-period" />
		</th>
		<td><acme:print value="${averageExecutionPeriod}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.deviation-execution-period" />
		</th>
		<td><acme:print value="${deviationExecutionPeriod}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.maximum-execution-period" />
		</th>
		<td><acme:print value="${maximumExecutionPeriod}" /></td>
	</tr>
	<tr>
		<th scope="row"><acme:message
				code="administrator.dashboard.form.label.minimum-execution-period" />
		</th>
		<td><acme:print value="${minimumExecutionPeriod}" /></td>
	</tr>

</table>

<div>
	<canvas id="canvaswp"></canvas>
</div>
<h2>
	<acme:message
		code="administrator.dashboard.form.title.application-statuses" />
</h2>

<div>
	<canvas id="canvas"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(
			function a() {
				var data = {
					labels : [
							"PENDING", "ACCEPTED", "REJECTED"
					],
					datasets : [
						{
							data : [
									<jstl:out value="${ratioOfPendingApplications}"/>, <jstl:out value="${ratioOfAcceptedApplications}"/>,
									<jstl:out value="${ratioOfRejectedApplications}"/>
							]
						}
					]
				};
				var options = {
					scales : {
						yAxes : [
							{
								ticks : {
									suggestedMin : 0.0,
									suggestedMax : 1.0
								}
							}
						]
					},
					legend : {
						display : false
					}
				};

				var canvas, context;

				canvas = document.getElementById("canvas");
				context = canvas.getContext("2d");
				new Chart(context, {
					type : "bar",
					data : data,
					options : options
				});
			});
</script>
