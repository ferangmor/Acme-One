
package acme.entities.tasks;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Manager;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task extends DomainEntity {
	
	protected static final long	serialVersionUID	= 1L;
	
	@Length(min = 1, max = 80)
	@NotBlank
	protected String title;
	
	@Future
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date startTime;
	
	@Future
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date endTime;
	
	@NotBlank
	@Length(min = 1, max = 500)
	@Column(length = 500)
	protected String description;
	
	@URL
	protected String info;
	
	@NotNull
	protected Boolean isPublic;
	
	public void setIsPublic(final Boolean a) {
		this.isPublic = a;
	}
	
	
	@Min(0)
    public double workload;
	
	
	// Derived variables
	
	@Transient
	public double getDays() {
	    return (double) (this.endTime.getTime() - this.startTime.getTime())/(1000*60*60*24);
	}
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Manager manager;
	
	// Validations
	
//	@Transient
//	public Boolean isInFormat(final String date){
//		return date.matches("^\\d{4}/\\d{2}/\\d{2} \\d{2}/\\\\d{2}");
//	}
	
}
