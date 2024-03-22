package com.example.patterns.builder;

import java.time.LocalDate;

public class TourDirector {

    private final TourPlanBuilder tourPlanBuilder;

    public TourDirector(TourPlanBuilder tourPlanBuilder) {
        this.tourPlanBuilder = tourPlanBuilder;
    }

    public TourPlan testTrip() {
        return tourPlanBuilder.title("테스트 빌더")
                .nightsAndDays(1, 2)
                .startDate(LocalDate.of(2022, 12, 24))
                .whereToStay("호텔")
                .addPlan(0, "체크인")
                .addPlan(0, "구경")
                .getPlan();
    }

}
