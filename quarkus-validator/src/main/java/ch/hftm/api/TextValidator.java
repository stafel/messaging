package ch.hftm.api;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import ch.hftm.entities.ValidationText;

@ApplicationScoped
public class TextValidator {

    @Incoming("requests")
    @Outgoing("validations")
    public ValidationText updatePostValidity(ValidationText validText) {
        System.out.println("Received " + validText);
        return validText;
    }
}
