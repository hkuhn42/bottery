# Bottery
A Java based framework for chatbots

The goal of this framework is to provide a framework for writing chat service neutral bots without the need of a cloud 
environment. Currently the projects tries to provide basic plumbing like i18n, message storage and configuration as well
as a framework for simple scripted bots. The api already allows for using nlp and machine learning but will provide 
implementations and examples in a later release.

## Gettings started

There is a number of examples in the examples package however a very basic echo bot on the console takes no more than:

```java
public EchoBot() {
    super(new ConsoleConnector());

    setWelcomeDialog(new Utterance() {
        @Override
        public IModel<String> getText(IActivity request, ISession session) {
            return new Model<String>(request.getText());
        }
    });
}
```

## History
The original implementation was part of an effort to bring voice and text assistants to openhab and eclipse smarthome.
Some of the concepts for voice made it into the eclipse smarthome core and the rest eventually evolved into this framework.

## Name
The name is a combination of the words bot, battery and potter