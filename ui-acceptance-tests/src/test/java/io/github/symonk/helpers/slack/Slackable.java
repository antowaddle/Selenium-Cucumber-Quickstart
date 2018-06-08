package io.github.symonk.helpers.slack;

@FunctionalInterface
public interface Slackable {

  void sendMessageToSlack(String messageContents);
}
