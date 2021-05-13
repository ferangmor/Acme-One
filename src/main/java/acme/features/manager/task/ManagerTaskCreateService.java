package acme.features.manager.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerTaskCreateService implements AbstractCreateService<Manager, Task> {
	
	@Autowired
	protected ManagerTaskRepository repository;

	
	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "startTime", "endTime", "description", "isPublic","workload", "info");
	}

	@Override
	public Task instantiate(final Request<Task> request) {
		assert request != null;

		Task result;

		Manager manager;
		manager = this.repository.findOneManagerById(request.getPrincipal().getActiveRoleId());
		
		result = new Task();
		result.setTitle("Go to Mercadona");
		result.setDescription("You have to buy guacamole, chicken, tortitas, shredded cheese, peppers and Mexican sauce to make the burritos for dinner with Millers family.");
		result.setInfo("http://example.org");
		
		result.setManager(manager);
		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		if (!errors.hasErrors("endTime")) {
			errors.state(request, entity.getEndTime().after(entity.getStartTime()), "endTime", "manager.task.form.error.isBefore");	
		}
		
		if (!errors.hasErrors("workload")) {
			final double executionPeriodInHours;
			long executionPeriod;
			
			executionPeriod = entity.getEndTime().getTime()-entity.getStartTime().getTime();
			
			executionPeriodInHours = executionPeriod / 3600000.0; //Un minuto son 60.000 milisegundos 
			
			errors.state(request, entity.getWorkload()<= executionPeriodInHours, "workload", "manager.task.form.error.workload");
			
		}
				
		final List<Configuration> listConfigurations = new ArrayList<>(this.repository.getConfiguration());
		
		final Configuration confEng = listConfigurations.get(0);
		final Configuration confEsp = listConfigurations.get(1);
		
		if (!errors.hasErrors("title")) {
			errors.state(request, !(confEng.spamValidation(entity.getTitle()) || confEsp.spamValidation(entity.getTitle())), "title", "manager.task.form.error.spam");
		}
		
		if (!errors.hasErrors("description")) {
			errors.state(request, !(confEng.spamValidation(entity.getDescription()) || confEsp.spamValidation(entity.getDescription())), "description", "manager.task.form.error.spam");
		}
	}

	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Task> request, final Response<Task> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}
}
