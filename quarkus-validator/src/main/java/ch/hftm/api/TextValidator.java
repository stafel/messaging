package ch.hftm.api;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import ch.hftm.entities.ValidationText;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class TextValidator {

    @Incoming("requests")
    @Outgoing("validations")
    public Uni<ValidationText> updatePostValidity(Uni<ValidationText> validText) {
        return validText.map( p -> {
            if (p.getText().equals("wow")) {
                p.setValid(false);
            }
            return p;
        });
    }
}
