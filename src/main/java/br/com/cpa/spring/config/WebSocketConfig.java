package br.com.cpa.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${broker.host}")
    private String brokerHost;

    @Value("${broker.port}")
    private int brokerPort;

    @Value("${broker.username}")
    private String brokerUser;

    @Value("${broker.password}")
    private String brokerPass;

    @Value("${broker.enabled}")
    private Boolean brokerEnabled;

    @Value("${broker.brokerVirtualHost}")
    private String brokerVirtualHost;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();
        // setHandshakeHandler(new CustomHandshakeHandler()).withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        if (brokerEnabled) {
            registry
                .enableStompBrokerRelay(
                    "/topic",
                    "/queue"
                )
                .setSystemLogin(brokerUser)
                .setSystemPasscode(brokerPass)
                .setClientLogin(brokerUser)
                .setClientPasscode(brokerPass)
                .setRelayHost(brokerHost)
                .setRelayPort(brokerPort)
                .setAutoStartup(true)
                .setSystemHeartbeatReceiveInterval(10000)
                .setSystemHeartbeatSendInterval(10000);
            registry.setApplicationDestinationPrefixes("/app");
            registry.setUserDestinationPrefix("/user");
            // .setUserDestinationBroadcast("/user");
            // .setUserRegistryBroadcast("/chatroom/log-user-registry");

            if (!brokerVirtualHost.equals("")) {
                // registry.setVirtualHosts(brokerVirtualHost);
            }
        } else {
            registry.enableSimpleBroker("/chatroom", "/user");
            registry.setApplicationDestinationPrefixes("/app");
            registry.setUserDestinationPrefix("/user");
        }

    }

    // @Override
    // public boolean configureMessageConverters(List<MessageConverter>
    // messageConverters) {
    // DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
    // resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
    // MappingJackson2MessageConverter converter = new
    // MappingJackson2MessageConverter();
    // converter.setObjectMapper(new ObjectMapper());
    // converter.setContentTypeResolver(resolver);
    // messageConverters.add(converter);
    // return false;
    // }
}
