package edu.hm;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import edu.hm.schatter.shareit.businesslayer.MediaService;
import edu.hm.schatter.shareit.businesslayer.MediaServiceImpl;
import edu.hm.schatter.shareit.persistence.MediaPersistence;
import edu.hm.schatter.shareit.persistence.MediaPersistenceImpl;

/**
 * Context Listener to enable usage of google guice together with jersey.
 * @author <a mailto:axel.boettcher@hm.edu>Axel Böttcher</a>
 *
 */
public class ShareitServletContextListener extends GuiceServletContextListener {

    private static final Injector INJECTOR = Guice.createInjector(new ServletModule() {
        @Override
        protected void configureServlets() {
            bind(MediaService.class).to(MediaServiceImpl.class);
            bind(MediaPersistence.class).to(MediaPersistenceImpl.class);
        }
    });

    @Override
    protected Injector getInjector() {
        return INJECTOR;
    }

    /**
     * This method is only required for the HK2-Guice-Bridge in the Application class.
     * @return Injector instance.
     */
    static Injector getInjectorInstance() {
        return INJECTOR;
    }

}
