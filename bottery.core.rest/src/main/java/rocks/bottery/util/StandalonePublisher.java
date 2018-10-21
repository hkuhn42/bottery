/**
 * 
 */
package rocks.bottery.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.jaxrs.JAXRSBindingFactory;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

/**
 * @author Harald Kuhn
 *
 */
public class StandalonePublisher {

	public static Server publish(Object api, String address) {

		try {
			JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
			sf.setResourceClasses(api.getClass());

			sf.setResourceProvider(api.getClass(), new SingletonResourceProvider(api));
			List<Object> providers = new ArrayList<>();
			JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
			provider.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			provider.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			providers.add(provider);
			sf.setProviders(providers);
			sf.setAddress(address);
			BindingFactoryManager manager = sf.getBus().getExtension(BindingFactoryManager.class);
			JAXRSBindingFactory factory = new JAXRSBindingFactory();
			factory.setBus(sf.getBus());
			sf.getBus().getInInterceptors().add(new LoggingInInterceptor());
			manager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID, factory);
			return sf.create();
		}
		catch (RuntimeException e) {
			Logger.getLogger(StandalonePublisher.class).error("failed to publish service", e);
			throw e;
		}
	}

}
