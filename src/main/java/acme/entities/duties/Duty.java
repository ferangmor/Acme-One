
package acme.entities.duties;

import java.math.BigDecimal;
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

import acme.entities.roles.Officer;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Duty extends DomainEntity {
	
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
	
	public double getExecutionPeriodInHours() {
		final double executionPeriodInHours;
		long executionPeriod;
		double res = 0.0;
		
		if(this.startTime != null) {
			
			executionPeriod = this.endTime.getTime() - this.startTime.getTime();
			
			executionPeriodInHours = executionPeriod / 3600000.0; //Un minuto son 60.000 milisegundos
			
			final BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(executionPeriodInHours));
			
			final int intValue2 = bigDecimal2.intValue();
			final double decimalPart2 = bigDecimal2.subtract(new BigDecimal(intValue2)).doubleValue();
			
			final double decimalFinal = (decimalPart2 * 60) / 100.0;
			
			res = intValue2 + decimalFinal;
			
		}
		
		return res;
	}
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Officer officer;
	
}
