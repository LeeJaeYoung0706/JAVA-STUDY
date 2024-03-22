package com.example.patterns.builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DefaultTourBuilder implements TourPlanBuilder {

    private TourPlan tourPlan;

    public TourPlanBuilder newInstance(){
        this.tourPlan = new TourPlan();
        return this;
    }

    @Override
    public TourPlanBuilder nightsAndDays(int nights, int days) {
       tourPlan.setNights(nights);
       tourPlan.setDays(days);
       return this;
    }

    @Override
    public TourPlanBuilder title(String title) {
        tourPlan.setTitle(title);
        return this;
    }

    @Override
    public TourPlanBuilder startDate(LocalDate startDate) {
        tourPlan.setStartDate(startDate);
        return this;
    }

    @Override
    public TourPlanBuilder whereToStay(String whereToStay) {
        tourPlan.setWhereToStay(whereToStay);
        return this;
    }

    @Override
    public TourPlanBuilder addPlan(int day, String plan) {
        if (tourPlan.getPlans() == null) {
            tourPlan.setPlans(new ArrayList<>());
        }

        tourPlan.getPlans().add(new DetailPlan(day, plan));
        return this;
    }

    @Override
    public TourPlan getPlan() {
        return this.tourPlan;
    }
}
