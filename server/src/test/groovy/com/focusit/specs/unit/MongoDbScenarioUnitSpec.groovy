package com.focusit.specs.unit

import com.focusit.model.Experiment
import com.focusit.repository.EventRepositoryCustom
import com.focusit.repository.ExperimentRepository
import com.focusit.scenario.MongoDbScenario
import spock.lang.Specification


/**
 * Created by doki on 23.05.16.
 */
class MongoDbScenarioUnitSpec extends Specification {

    EventRepositoryCustom eventRepositoryCustom = Mock();
    ExperimentRepository experimentRepository = Mock();
    Experiment experiment = new Experiment();
    MongoDbScenario scenario = new MongoDbScenario(experiment, eventRepositoryCustom, experimentRepository);

    def "next will zero position event when experiment's position is bigger than steps count"() {
        given:
        experiment.position = 1;
        experiment.steps = 2;
        when:
        scenario.next();
        then:
        experiment.position == 0;

        when:
        experiment.position = 2;
        experiment.steps = 2;
        scenario.next();
        then:
        experiment.position == 0;
    }

    def "setPosition persist position"() {
        given:
        experiment.position = 0;
        when:
        scenario.setPosition(123);
        then:
        1 * experimentRepository.save({ it.position == 123 });
    }
}