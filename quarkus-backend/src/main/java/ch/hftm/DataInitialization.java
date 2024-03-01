package ch.hftm;

import ch.hftm.repositories.PostRepository;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;
import jakarta.inject.Inject;

@ApplicationScoped
public class DataInitialization {
    @Inject
    PostRepository postRepository;

    @Transactional
    public void init(@Observes StartupEvent event) {
        // init data if none available
        if (postRepository.listAll().isEmpty()) {
            postRepository.addTestPosts();
        }
    }
}
