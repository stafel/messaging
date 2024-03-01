package org.acme;

import jakarta.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.mutiny.Multi;

@ApplicationScoped
public class MessagingTesting {

    /* 
    @Outgoing("source")
    public Multi<String> source() {
        return Multi.createFrom().items("hallo", "lol", "böse", "quarkus", "fans");
    } */

    /*
    // io.smallrye.reactive.messaging.providers.wiring.TooManyDownstreamCandidatesException
    @Incoming("source")
    public void preSink(String word) {
        System.out.println("in: " + word);
    }
    */

    @Incoming("source")
    @Outgoing("processed-a")
    public String toUpperCase(String payload) {
        return payload.toUpperCase();
    }

    @Incoming("processed-a")
    @Outgoing("processed-b")
    public String beNice(String payload) {
        if (payload.equals("BÖSE")) {
            return "LIEBE";
        }
        return payload;
    }

    @Incoming("processed-b")
    @Outgoing("processed-c")
    public Multi<String> filterLittleWords(Multi<String> input) {
        return input.filter(p -> p.length() >= 6);
    }

    @Incoming("processed-c")
    public void sink(String word) {
        System.out.println(">> " + word);
    }
    
}