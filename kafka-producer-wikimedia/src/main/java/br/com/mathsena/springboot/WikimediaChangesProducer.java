package br.com.mathsena.springboot;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    @Value("${kafka.topic.name}")
    private String topic;

    @Value("${wikimedia.stream.url}")
    private String url;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() {
        EventHandler eventHandler = new WikimediaChangeHandler(kafkaTemplate, topic);
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));

        try (EventSource eventSource = builder.build()) {
            eventSource.start();
            LOGGER.info("Event source started");

            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                LOGGER.error("Error during sleep", e);
                Thread.currentThread().interrupt();
            }

        } catch (Exception e) {
            LOGGER.error("Error in event source", e);
        }

        LOGGER.info("Event source ended");
    }
}
