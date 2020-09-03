package com.prisonerprice.reactiveMongoTesting.service;

import com.prisonerprice.reactiveMongoTesting.config.TestConfig;
import com.prisonerprice.reactiveMongoTesting.model.People;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Import(TestConfig.class)
@ActiveProfiles("test")
public class PeopleServiceTest {
    private static final MongodStarter starter = MongodStarter.getDefaultInstance();
    private MongodExecutable _mongodExe;
    private MongodProcess _mongod;

    @Autowired
    private PeopleService peopleService;

    @Before
    public void init() throws IOException {
        _mongodExe = starter.prepare(new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net("localhost", 12345, Network.localhostIsIPv6()))
                .build());
        _mongod = _mongodExe.start();
    }

    @Test
    public void getAllPeopleTest() {
        People people = new People();
        people.setFirstName("dummy");
        people.setLastName("dizzy");
        peopleService.savePeople(people);
        Assert.assertEquals(1, peopleService.getAllPeople().size());
    }

    @After
    public void tearDown() {
        _mongod.stop();
        _mongodExe.stop();
    }

}
