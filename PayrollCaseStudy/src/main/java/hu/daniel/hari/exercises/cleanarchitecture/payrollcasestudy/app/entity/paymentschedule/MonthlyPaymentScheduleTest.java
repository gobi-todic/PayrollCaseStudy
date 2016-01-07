package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.PaymentSchedule.NotPaydayException;
import hu.daniel.hari.testthis.data.inmemory.entity.MonthlyPaymentScheduleImpl;

import java.time.LocalDate;

import org.junit.Test;

public class MonthlyPaymentScheduleTest {

	MonthlyPaymentSchedule monthlyPaymentSchedule = new MonthlyPaymentScheduleImpl();

	@Test
	public void isPaydayOnLastDayOfMonth_ShouldBeTrue() throws Exception {
		assertThat(monthlyPaymentSchedule.isPayDate(LocalDate.of(2015, 12, 31)), is(true));
		assertThat(monthlyPaymentSchedule.isPayDate(LocalDate.of(2015, 11, 30)), is(true));
	}
	@Test
	public void isPaydayOnNotLastDayOfMonth_ShouldBeFalse() throws Exception {
		assertThat(monthlyPaymentSchedule.isPayDate(LocalDate.of(2015, 11, 24)), is(false));
		assertThat(monthlyPaymentSchedule.isPayDate(LocalDate.of(2015, 12, 01)), is(false));
		assertThat(monthlyPaymentSchedule.isPayDate(LocalDate.of(2015, 12, 02)), is(false));
	}
	
	@Test
	public void getIntervalOnPayday() {
		DateInterval dateInterval = monthlyPaymentSchedule.getPayInterval(LocalDate.of(2015, 12, 31));
		assertThat(dateInterval.from, 	is(LocalDate.of(2015, 12, 01)));
		assertThat(dateInterval.to, 	is(LocalDate.of(2015, 12, 31)));
	}

	@Test(expected=NotPaydayException.class)
	public void getIntervalOnNonPayday_ShouldThrowException() {
		monthlyPaymentSchedule.getPayInterval(LocalDate.of(2015, 11, 1));
	}
	
}