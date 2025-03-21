FROM docker:24.0.6

USER root

RUN apk add --no-cache \
    git \
    curl \
    bash \
    jq \
    yq \
    github-cli \
    less \
  && addgroup -S jenkins \
  && adduser -S jenkins -G jenkins

USER jenkins
