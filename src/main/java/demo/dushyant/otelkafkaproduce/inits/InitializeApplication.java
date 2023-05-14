package demo.dushyant.otelkafkaproduce.inits;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;

import demo.dushyant.otelkafkaproduce.configs.Topic;
import jakarta.annotation.PostConstruct;

@Component("initApp")
@ConfigurationProperties(prefix = "app")
public class InitializeApplication {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private KafkaAdmin kafkaAdmin;

    private List<Topic> topics = new ArrayList<>();

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    private NewTopic createTopic(Topic topic) {
        return TopicBuilder.name(topic.name()).partitions(topic.partitions()).replicas(topic.replicationFactor()).build();
    }

    @PostConstruct
    public void init() {
        if((topics == null) || (topics.isEmpty())) {
            throw new RuntimeException("Error reading topic");
        } else {
			topics.forEach(e -> {
                logger.info("Creating topic {}",e.name());
                kafkaAdmin.createOrModifyTopics(createTopic(e));
            });
		}

    }

}
